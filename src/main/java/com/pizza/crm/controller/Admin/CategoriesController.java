package com.pizza.crm.controller.Admin;

import com.pizza.crm.model.Categories;
import com.pizza.crm.service.security.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @RequestMapping("/acotegories")
    public String adminCategories(Model model) {

        model.addAttribute("Categories", categoriesService.getAll());

        return "adminCategories";
    }

    @RequestMapping(value =  "/acotegories/add", method = RequestMethod.POST)
    public String adminAddCategories(@RequestParam String name, Model model) {

        if (!name.isEmpty()) {
            categoriesService.save(new Categories(name));
        }

        return "redirect:/acotegories";
    }

    @RequestMapping(value =  "/acotegories/del", method = RequestMethod.GET)
    public String adminDeleteCategories(@RequestParam String id, Model model) {

        if (!id.isEmpty()) {
            categoriesService.deleteById(new Long(id));
        }

        return "redirect:/acotegories";
    }

    @RequestMapping(value =  "/acotegories/edit", method = RequestMethod.POST)
    public String adminEditCategories(@RequestParam String id, @RequestParam String name, Model model) {

        if (!id.isEmpty() || !name.isEmpty()) {
            Categories categories = categoriesService.findById(new Long(id)).get();
            categories.setName(name);
            categoriesService.save(categories);
        }

        return "redirect:/acotegories";
    }



}
