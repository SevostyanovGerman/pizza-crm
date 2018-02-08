package com.pizza.crm.controller.admin;

import com.pizza.crm.model.AddedCategory;
import com.pizza.crm.model.Categories;
import com.pizza.crm.service.AddedCategoryService;
import com.pizza.crm.service.security.CategoriesService;
import com.pizza.crm.service.security.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private AddedCategoryService addedCategoryService;

    @Autowired
    private DishService dishService;

    @RequestMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("Categories", categoriesService.getAll());
        model.addAttribute("addedCategories", addedCategoryService.findAllCategories());
        return "adminCategories";
    }

    @RequestMapping(value = "/categories/add", method = RequestMethod.POST)
    public String addCategories(@ModelAttribute("Categories") @Validated Categories categories, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            categoriesService.save(categories);
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/categories/del", method = RequestMethod.GET)
    public String deleteCategories(@Valid @RequestParam Long id) {
        categoriesService.deleteById(id);
        return "redirect:/categories";
    }

    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    public String updateCategories(@ModelAttribute("Categories") @Validated Categories categories, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            categoriesService.updateCategoriesName(categories);
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/categories/update", method = RequestMethod.GET)
    public String updateCategories(@Valid @RequestParam(name = "id") Long id, Model model) {
        Categories categories = categoriesService.findById(id).get();
        model.addAttribute("categoriesName", categories.getName());
        model.addAttribute("categoriesId", categories.getId());
        model.addAttribute("categoriesDish", categories.getDish());
        model.addAttribute("allDish", dishService.getAll());
        return "moreCategoriesInfo";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addCategory(AddedCategory category){
        if (addedCategoryService.getCategoryByName(category.getName()) == null){
            addedCategoryService.save(category);
        } else {
            AddedCategory db = addedCategoryService.getCategoryByName(category.getName());
            db.setColor(category.getColor());
            addedCategoryService.save(db);
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public String deleteCategory(String name){
        AddedCategory db = addedCategoryService.getCategoryByName(name);
        if (db != null){
            addedCategoryService.delete(db.getId());
        }
        return "redirect:/categories";
    }
}
