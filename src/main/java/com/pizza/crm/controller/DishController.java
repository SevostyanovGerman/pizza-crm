package com.pizza.crm.controller;

import com.pizza.crm.model.Category;
import com.pizza.crm.model.Dish;
import com.pizza.crm.service.security.CategoryService;
import com.pizza.crm.service.security.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

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
        Optional<Dish> dishOptional  = dishService.findById(id);
        if (dishOptional.isPresent()) {
            model.addAttribute("dish", dishOptional.get());
            model.addAttribute("allDishCategories", dishService.getAvailableCategories(dishOptional.get()));
            return "admin/dish/edit";
        }
        return "admin/dish";
    }

    @PostMapping("/update")
    public String updateDish(@RequestBody Dish dish) {
        dishService.updateDishCategories(dish);
        return "redirect:/admin/dish";
    }

    @GetMapping("/create")
    public String newDish(Model model) {
        model.addAttribute("dish", new Dish("Новое блюдо"));
        model.addAttribute("allDishCategories", categoryService.getAll());
        return "admin/dish/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteById(id);
        return "redirect:/admin/dish";
    }
}
