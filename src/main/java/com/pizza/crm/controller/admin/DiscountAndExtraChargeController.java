package com.pizza.crm.controller.admin;

import com.pizza.crm.service.CategoryService;
import com.pizza.crm.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DiscountAndExtraChargeController {

    private final DiscountService discountService;

    private final CategoryService categoryService;

    @Autowired
    public DiscountAndExtraChargeController(DiscountService discountService, CategoryService categoryService) {
        this.discountService = discountService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/discountandextracharge")
    public String show(Model model) {
        model.addAttribute("discounts", discountService.findAll());
        return "admin/DiscountAndExtraCharge/discountandextracharge";
    }

    @GetMapping("/discountandextracharge/update/step1")
    public String updateStepOne() {
        return "admin/DiscountAndExtraCharge/stepOne";
    }

    @RequestMapping("/discountandextracharge/new/step1")
    public String addNewStepOne() {
        return "admin/DiscountAndExtraCharge/stepOne";
    }

    @RequestMapping("/discountandextracharge/step2")
    public String stepTwo() {
        return "admin/DiscountAndExtraCharge/stepTwo";
    }

    @RequestMapping("/discountandextracharge/step3/")
    public String stepThree(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "admin/DiscountAndExtraCharge/stepThree";
    }

    @RequestMapping("/discountandextracharge/step4")
    public String stepFour() {
        return "admin/DiscountAndExtraCharge/stepFour";
    }

}
