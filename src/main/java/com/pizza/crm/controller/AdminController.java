package com.pizza.crm.controller;

import com.pizza.crm.model.AddedCategory;
import com.pizza.crm.service.AddedCategoryService;
import com.pizza.crm.service.security.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AddedCategoryService addedCategoryService;

    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping("/admin")
    public String admin(Model model){
        model.addAttribute("categories", addedCategoryService.findAllCategories());
        return "adminPage";
    }

    @RequestMapping(value = "/admin/getinfo", method = RequestMethod.GET)
    @ResponseBody
    public AddedCategory getCategory(String name){
        return addedCategoryService.getCategoryByName(name);
    }

    @RequestMapping(value = "/admin/findall", method = RequestMethod.GET)
    @ResponseBody
    public List<AddedCategory> findCategories(){
        return addedCategoryService.findAllCategories();
    }
}
