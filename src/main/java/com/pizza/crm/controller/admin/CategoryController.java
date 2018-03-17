package com.pizza.crm.controller.admin;

import com.pizza.crm.model.AddedCategory;
import com.pizza.crm.model.Category;
import com.pizza.crm.model.Dish;
import com.pizza.crm.service.AddedCategoryService;
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

    private final CategoryService categoryService;

    private final AddedCategoryService addedCategoryService;

    private final DishService dishService;

    @Autowired
    public CategoryController(CategoryService categoryService, AddedCategoryService addedCategoryService, DishService dishService) {
        this.categoryService = categoryService;
        this.addedCategoryService = addedCategoryService;
        this.dishService = dishService;
    }

    @RequestMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("addedCategories", addedCategoryService.findAllCategories());
        return "adminCategories";
    }

    @RequestMapping(value = "/categories/add", method = RequestMethod.POST)
    public String addCategories(@ModelAttribute("category") @Validated Category category, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            categoryService.save(category);
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/categories/del", method = RequestMethod.GET)
    public String deleteCategories(@Valid @RequestParam Long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }

    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    public String updateCategories(@ModelAttribute("category") @Validated Category category, BindingResult bindingResult) {
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

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addCategory(AddedCategory category) {
        if (addedCategoryService.getCategoryByName(category.getName()) == null) {
            addedCategoryService.save(category);
        } else {
            AddedCategory db = addedCategoryService.getCategoryByName(category.getName());
            db.setColor(category.getColor());
            addedCategoryService.save(db);
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public String deleteCategory(String name) {
        AddedCategory db = addedCategoryService.getCategoryByName(name);
        if (db != null) {
            addedCategoryService.delete(db.getId());
        }
        return "redirect:/categories";
    }

    @PostMapping(value = "/update/categoriesdish/{id}")
    public @ResponseBody
    ResponseEntity<?> updateCategories1(@RequestBody @Validated Set<Dish> dish,
                                        @PathVariable("id") @Validated Long id,
                                        BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            categoryService.updateCategoriesDish(id, dish);
        }
        return ResponseEntity.ok("");
    }
}
