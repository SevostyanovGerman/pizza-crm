package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.model.Decree;
import com.pizza.crm.model.Dish;
import com.pizza.crm.model.Validity;
import com.pizza.crm.service.DecreeService;
import com.pizza.crm.service.ValidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class DecreeControllerRest {

    private final DecreeService decreeService;
    private final ValidityService validityService;

    @Autowired
    public DecreeControllerRest(DecreeService decreeService, ValidityService validityService) {
        this.decreeService = decreeService;
        this.validityService = validityService;
    }

    @RequestMapping(value = "/get/decree")
    public ResponseEntity<?> getDecree() {
        Collection<Decree> decrees = decreeService.getAll();
        return ResponseEntity.ok(decrees);
    }

    @RequestMapping(value = "/save/decree")
    public ResponseEntity<?> saveDecree(@RequestBody Decree decree) {
        Validity validity = validityService.findByNameValidity(decree.getValidities().get(0).getNameValidity());
        List<Validity> validities = new ArrayList<>();
        validities.add(validity);
        decree.setValidities(validities);
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
