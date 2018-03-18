package com.pizza.crm.controller.admin.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizza.crm.model.DishQuickMenu;
import com.pizza.crm.model.QuickMenu;
import com.pizza.crm.service.QuickMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class QuickMenuControllerRest {

    private final QuickMenuService quickMenuService;

    @Autowired
    public QuickMenuControllerRest(QuickMenuService quickMenuService) {
        this.quickMenuService = quickMenuService;
    }

    @RequestMapping(value = "/get/quickmenu/{day}")
    public ResponseEntity<?> getQuickMenu(@PathVariable("day") @Validated int day) {
        Collection<QuickMenu> quickMenu = quickMenuService.getQuickMenuByDay(day);
        return ResponseEntity.ok(quickMenu);
    }

    @RequestMapping(value = "/update/quickmenu/{day}")
    public ResponseEntity<?> updateQuickMenu(@RequestBody @Validated Collection<QuickMenu> quickMenu, @PathVariable("day") @Validated int day, BindingResult bindingResult) throws JsonProcessingException {
        if (!bindingResult.hasErrors()) {
            quickMenuService.updateQuickMenu(day, quickMenu);
        }
        return ResponseEntity.ok("");
    }


}
