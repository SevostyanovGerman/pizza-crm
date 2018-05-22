package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.model.ModifierProperty;
import com.pizza.crm.service.SchemeModifiersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchemeModifiersControllerRest {

    private final SchemeModifiersService schemeModifiersService;

    @Autowired
    public SchemeModifiersControllerRest(SchemeModifiersService schemeModifiersService) {
        this.schemeModifiersService = schemeModifiersService;
    }

    @PostMapping("schemeModifiers/getListModifiers")
    List<ModifierProperty> getListModifier(@RequestParam String name) {
        return schemeModifiersService.getByName(name).getModifierPropertyList();
    }

    @PostMapping("schemeModifiers/delete")
    void deleteSchemeModifiers(@RequestParam String name) {
        schemeModifiersService.delete(name);
    }


}
