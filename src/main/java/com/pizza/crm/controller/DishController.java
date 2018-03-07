package com.pizza.crm.controller;

import com.pizza.crm.model.Dish;
import com.pizza.crm.service.CategoryService;
import com.pizza.crm.service.DishService;
import com.pizza.crm.service.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/dish")
public class DishController {

    private DishService dishService;

    private CategoryService categoryService;

    private IngredientService ingredientService;

    public DishController(DishService dishService, CategoryService categoryService, IngredientService ingredientService) {
        this.dishService = dishService;
        this.categoryService = categoryService;
        this.ingredientService = ingredientService;
    }

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
            model.addAttribute("allDishIngredients", dishService.getAvailableIngredients(dishOptional.get()));
            return "admin/dish/edit";
        }
        return "admin/dish";
    }

    @PostMapping("/update")
    public String updateDish(@RequestBody Dish dish) {
            dishService.save(dish);
        return "redirect:/admin/dish";
    }

    @GetMapping("/create")
    public String newDish(Model model) {
        model.addAttribute("dish", new Dish("Новое блюдо"));
        model.addAttribute("allDishCategories", categoryService.getAll());
        model.addAttribute("allDishIngredients", ingredientService.getAll());
        return "admin/dish/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteById(id);
        return "redirect:/admin/dish";
    }
}
