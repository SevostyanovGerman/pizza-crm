package com.pizza.crm.controller;

import com.pizza.crm.model.Category;
import com.pizza.crm.model.Dish;
import com.pizza.crm.service.CategoryService;
import com.pizza.crm.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;


@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @RequestMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("Categories", categoryService.getAll());
        return "adminCategories";
    }

    @RequestMapping(value = "/categories/add", method = RequestMethod.POST)
    public String addCategories(@ModelAttribute("Categories") @Validated Category categories, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            categoryService.save(categories);
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/categories/del", method = RequestMethod.GET)
    public String deleteCategories(@Valid @RequestParam Long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }

    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    public String updateCategories(@ModelAttribute("Categories") @Validated Category category, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            categoryService.updateCategoriesName(category);
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/categories/update", method = RequestMethod.GET)
    public String updateCategories(@Valid @RequestParam(name = "id") Long id, Model model) {
        Category category = categoryService.findById(id).get();
        model.addAttribute("categoriesName", category.getName());
        model.addAttribute("categoriesId", category.getId());
        model.addAttribute("categoriesDish", category.getDishes());
        model.addAttribute("allDish", dishService.getAll());
        return "moreCategoriesInfo";
    }

    @PostMapping(value = "/update/categoriesdish/{id}")
    public @ResponseBody ResponseEntity<?> updateCategories1(@RequestBody @Validated Set<Dish> dish,
                                               @PathVariable("id") @Validated long id,
                                               BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            categoryService.updateCategoriesDish(id, dish);
        }
        return ResponseEntity.ok("");
    }
}
