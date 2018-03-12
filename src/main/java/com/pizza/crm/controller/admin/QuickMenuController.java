package com.pizza.crm.controller.admin;

import com.pizza.crm.service.DishService;
import com.pizza.crm.service.QuickMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuickMenuController {


    private final QuickMenuService quickMenuService;

    private final DishService dishService;

    @Autowired
    public QuickMenuController(QuickMenuService quickMenuService, DishService dishService) {
        this.quickMenuService = quickMenuService;
        this.dishService = dishService;
    }

    @RequestMapping("/quickmenu")
    public String quickMenu(Model model) {
        model.addAttribute("products", dishService.getAll());
        return "admin/quickmenu";
    }
}

