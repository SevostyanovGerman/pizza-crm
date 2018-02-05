package com.pizza.crm.controller;

import com.pizza.crm.dto.DishForm;
import com.pizza.crm.model.Dish;
import com.pizza.crm.service.CategoryService;
import com.pizza.crm.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"", "/"})
    public String dishList(Model model) {
        model.addAttribute("dishList", dishService.getAll());
        return "admin/dish/list";
    }

    @GetMapping("/{id}")
    public String dishDetails(@PathVariable Long id, Model model) {
        Dish dish = dishService.findById(id).orElseThrow(IllegalStateException::new);
        model.addAttribute("dish", dish);
        model.addAttribute("allDishCategories", dishService.getAvailableCategories(dish));
        return "admin/dish/edit";
    }

    @PostMapping("/update")
    public String updateDish(@RequestBody DishForm dishForm) {
        dishService.save(dishForm);
        return "redirect:/admin/dish";
    }

    @GetMapping("/create")
    public String newDish(Model model) {
        model.addAttribute("dish", new Dish(""));
        model.addAttribute("allDishCategories", categoryService.getAll());
        return "admin/dish/edit";
    }

    @GetMapping("/del/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteById(id);
        return "redirect:/admin/dish";
    }
}
