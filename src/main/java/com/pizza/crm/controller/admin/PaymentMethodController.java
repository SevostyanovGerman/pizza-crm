package com.pizza.crm.controller.admin;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.PaymentMethod;
import com.pizza.crm.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping("/admin/paymentMethod")
    public String getAllPaymentMethods(Model model) {
        model.addAttribute("allPaymentMethods", paymentMethodService.getAll());
        return "/admin/paymentMethod";
    }

    @GetMapping("/admin/paymentMethod/{id}")
    @ResponseBody
    public PaymentMethod getPaymentMethod(@PathVariable Long id) {
        return paymentMethodService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/admin/paymentMethod")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        paymentMethodService.save(paymentMethod);
    }

    @DeleteMapping("/admin/paymentMethod/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePaymentMethod(@PathVariable Long id) {
        paymentMethodService.deleteById(id);
    }

}
