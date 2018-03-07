package com.pizza.crm.controller;

import com.pizza.crm.service.AddedCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

    private AddedCategoryService categoryService;

    public OrderController(AddedCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/order")
    public String orderPage(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        return "order";
    }
}
