package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.model.Category;
import com.pizza.crm.model.Dish;
import com.pizza.crm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CategoryControllerRest {

    private CategoryService categoryService;

    @Autowired
    public CategoryControllerRest(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/update/categoriesdish/{id}")
    public ResponseEntity<?> updateCategories1(@RequestBody @Validated Set<Dish> dish, @PathVariable("id") @Validated long id, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            categoryService.updateCategoriesDish(id, dish);
        }
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/get/categoriesdish")
    public ResponseEntity<?> getDishToCategories(@RequestParam("name") @Validated String name) {
        Category category = categoryService.getCategoryByName(name);
        return ResponseEntity.ok(category.getDishes());
    }

}
