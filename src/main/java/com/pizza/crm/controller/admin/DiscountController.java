package com.pizza.crm.controller.admin;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.discount.Discount;
import com.pizza.crm.service.DiscountService;
import com.pizza.crm.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiscountController {

    private final DiscountService discountService;
    private final PaymentMethodService paymentMethodService;

    @Autowired
    public DiscountController(DiscountService discountService, PaymentMethodService paymentMethodService) {
        this.discountService = discountService;
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping("/admin/discount/list")
    public String getAllDiscounts(Model model) {
        model.addAttribute("allDiscounts", discountService.getAll());
        model.addAttribute("allPaymentMethods", paymentMethodService.getAll());
        return "/admin/discount/list";
    }

    @GetMapping("/admin/discount/edit/{id}")
    public String getDiscount(@PathVariable Long id, Model model) {
        Discount discount = discountService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("discount", discount);
        return "/admin/discount/edit";
    }

    @PostMapping("/admin/discount/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveDiscount(Discount discount) {
        discountService.save(discount);
        return "redirect:/admin/discount/list";
    }

    @DeleteMapping("/admin/discount/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDiscount(@PathVariable Long id) {
        discountService.deleteById(id);
    }



}
