package com.pizza.crm.controller.admin;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.PaymentType;
import com.pizza.crm.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PaymentTypeController {

    private final PaymentTypeService paymentTypeService;

    @Autowired
    public PaymentTypeController(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @GetMapping("/admin/paymentType")
    public String getAllPaymentTypes(Model model) {
        model.addAttribute("allPaymentTypes", paymentTypeService.getAll());
        return "/admin/paymentType";
    }

    @GetMapping("/admin/paymentType/all")
    @ResponseBody
    public List<PaymentType> getAllPaymentTypes() {
        return new ArrayList<>(paymentTypeService.getAll());
    }

    @GetMapping("/admin/paymentType/{id}")
    @ResponseBody
    public PaymentType getPaymentType(@PathVariable Long id) {
        return paymentTypeService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/admin/paymentType")
    @ResponseStatus(HttpStatus.OK)
    public void savePaymentType(PaymentType paymentType) {
        paymentTypeService.save(paymentType);
    }

    @DeleteMapping("/admin/paymentType/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePaymentType(@PathVariable Long id) {
        paymentTypeService.deleteById(id);
    }

}
