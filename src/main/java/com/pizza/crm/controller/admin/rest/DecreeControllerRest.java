package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.model.Decree;
import com.pizza.crm.model.Dish;
import com.pizza.crm.service.DecreeService;
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
public class DecreeControllerRest {

    private final DecreeService decreeService;

    @Autowired
    public DecreeControllerRest(DecreeService decreeService) {
        this.decreeService = decreeService;
    }

    @RequestMapping(value = "/get/decree")
    public ResponseEntity<?> getDecree() {
        Collection<Decree> decrees = decreeService.getAll();
        return ResponseEntity.ok(decrees);
    }

    @RequestMapping(value = "/save/decree")
    public ResponseEntity<?> saveDecree(@RequestBody Decree decree) {
        decreeService.save(decree);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/delete/decree/{id}")
    public ResponseEntity<?> deleteDecree(@PathVariable("id") @Validated long id) {
        decreeService.deleteById(id);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/get/decree/{id}")
    public ResponseEntity<?> getOneDecree(@PathVariable("id") @Validated long id) {
        Decree decree = decreeService.findById(id).get();
        return ResponseEntity.ok(decree);
    }
}
