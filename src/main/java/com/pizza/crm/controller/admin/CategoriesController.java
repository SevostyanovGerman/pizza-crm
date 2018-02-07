package com.pizza.crm.controller.admin;

import com.pizza.crm.model.Categories;
import com.pizza.crm.service.security.CategoriesService;
import com.pizza.crm.service.security.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private DishService dishService;

    @RequestMapping("/acotegories")
    public String categories(Model model) {
        model.addAttribute("Categories", categoriesService.getAll());
        return "adminCategories";
    }

    @RequestMapping(value = "/acotegories/add", method = RequestMethod.POST)
    public String addCategories(@ModelAttribute("Categories") @Validated Categories categories, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            categoriesService.save(categories);
        }
        return "redirect:/acotegories";
    }

    @RequestMapping(value = "/acotegories/del", method = RequestMethod.GET)
    public String deleteCategories(@Valid @RequestParam Long id) {
        categoriesService.deleteById(id);
        return "redirect:/acotegories";
    }

    @RequestMapping(value = "/acotegories/update", method = RequestMethod.POST)
    public String updateCategories(@ModelAttribute("Categories") @Validated Categories categories, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            categoriesService.updateCategoriesName(categories);
        }
        return "redirect:/acotegories";
    }

    @RequestMapping(value = "/acotegories/update", method = RequestMethod.GET)
    public String updateCategories(@Valid @RequestParam(name = "id") Long id, Model model) {
        Categories categories = categoriesService.findById(id).get();
        model.addAttribute("categoriesName", categories.getName());
        model.addAttribute("categoriesId", categories.getId());
        model.addAttribute("categoriesDish", categories.getDish());
        model.addAttribute("allDish", dishService.getAll());
        return "moreCategoriesInfo";
    }
}
