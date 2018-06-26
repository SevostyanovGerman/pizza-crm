package com.pizza.crm.controller.admin.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizza.crm.model.Dish;
import com.pizza.crm.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DishControllerRest {

    private final DishService dishService;

    @Autowired
    public DishControllerRest(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(value = "/get/dish")
    public ResponseEntity<?> productSearchPage() {
        Collection<Dish> dish = dishService.getAll();
        return ResponseEntity.ok(dish);
    }
}
