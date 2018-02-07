package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.model.Dish;
import com.pizza.crm.service.security.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CategoriesControllerRest {

    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(value = "/update/categoriesdish/{id}")
    public ResponseEntity<?> updateCategories1(@RequestBody @Validated Set<Dish> dish, @PathVariable("id") @Validated long id, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            categoriesService.updateCategoriesDish(id, dish);
        }
        return ResponseEntity.ok("");
    }
}
