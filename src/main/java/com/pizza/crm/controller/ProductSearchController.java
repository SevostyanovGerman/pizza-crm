package com.pizza.crm.controller;

import com.pizza.crm.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductSearchController {

    private final DishService dishService;

    @Autowired
    public ProductSearchController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/productSearch")
    public String productSearchPage(Model model) {
        model.addAttribute("products", dishService.getAll());
        return "/productSearch";
    }

}
