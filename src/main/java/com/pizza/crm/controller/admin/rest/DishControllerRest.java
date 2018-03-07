package com.pizza.crm.controller.admin.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizza.crm.model.Dish;
import com.pizza.crm.service.DishService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DishControllerRest {

    private DishService dishService;

    public DishControllerRest(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(value = "/get/dish")
    public String productSearchPage() throws JsonProcessingException {
        Collection<Dish> dish = dishService.getAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String dishAsString = objectMapper.writeValueAsString(dish);
        return dishAsString;
    }
}
