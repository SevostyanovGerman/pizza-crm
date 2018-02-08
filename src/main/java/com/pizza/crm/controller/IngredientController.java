package com.pizza.crm.controller;

import com.pizza.crm.model.Ingredient;
import com.pizza.crm.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/admin/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping({"", "/"})
    public String ingredientList(Model model) {
        model.addAttribute("ingredientList", ingredientService.getAll());
        return "/admin/ingredient/list";
    }

    @GetMapping("/{id}")
    public String ingredientDetails(@PathVariable Long id, Model model) {
        Optional<Ingredient> optionalIngredient = ingredientService.findById(id);
        if (optionalIngredient.isPresent()) {
            model.addAttribute("ingredient", optionalIngredient.get());
            return "/admin/ingredient/edit";
        }
        return "/admin/ingredient";
    }

    @PostMapping("/update")
    public String updateIngredient(Ingredient ingredient) {
        ingredientService.save(ingredient);
        return "redirect:/admin/ingredient";
    }

    @GetMapping("/create")
    public String createIngredient(Model model) {
        model.addAttribute(new Ingredient("Новый ингредиент", 0, "", ""));
        return "/admin/ingredient/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteById(id);
        return "redirect:/admin/ingredient";
    }

}
