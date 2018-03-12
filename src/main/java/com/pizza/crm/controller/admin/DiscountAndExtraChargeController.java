package com.pizza.crm.controller.admin;

import com.pizza.crm.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DiscountAndExtraChargeController {

    private DiscountService discountService;

    @Autowired
    public DiscountAndExtraChargeController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @RequestMapping("/discountandextracharge")
    public String show(Model model){
        model.addAttribute("discounts", discountService.findAll());
        return "admin/DiscountAndExtraCharge/discountandextracharge";
    }

    @RequestMapping("/discountandextracharge/new")
    public String addNew(){
        return "admin/DiscountAndExtraCharge/new";
    }

    @RequestMapping("/discountandextracharge/new2/{id}")
    public String addNew2(@PathVariable Long id, Model model){
        model.addAttribute("id", id);
        return "admin/DiscountAndExtraCharge/new2";
    }

    @RequestMapping("/discountandextracharge/new3/{id}")
    public String addNew3(@PathVariable Long id, Model model){
        model.addAttribute("id", id);
        return "admin/DiscountAndExtraCharge/new3";
    }

    @RequestMapping("/discountandextracharge/new4/{id}")
    public String addNew4(@PathVariable Long id, Model model){
        model.addAttribute("id", id);
        return "admin/DiscountAndExtraCharge/new4";
    }

}
