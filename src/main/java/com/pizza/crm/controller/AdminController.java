package com.pizza.crm.controller;

import com.pizza.crm.model.AddedCategory;
import com.pizza.crm.service.AddedCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class AdminController {
    @Autowired
    AddedCategoryService categoryService;

    @RequestMapping("/admin")
    public String admin(Model model){
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Category1");
        categories.add("Category2");
        categories.add("Category3");
        categories.add("Category4");
        model.addAttribute("categories", categories);
        return "adminPage";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String adminPage(AddedCategory category){
        categoryService.save(category);
        return "redirect:/admin";
    }

}
