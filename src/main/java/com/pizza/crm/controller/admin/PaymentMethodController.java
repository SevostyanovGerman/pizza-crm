package com.pizza.crm.controller.admin;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.PaymentMethod;
import com.pizza.crm.model.PaymentType;
import com.pizza.crm.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentMethodController {

    private static final class PaymentMethodData {
        private PaymentMethod paymentMethod;
        private PaymentType paymentType;

        public PaymentMethodData() {
        }

        public PaymentMethod getPaymentMethod() {
            return paymentMethod;
        }

        public PaymentType getPaymentType() {
            return paymentType;
        }

        public void setPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public void setPaymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
        }
    }

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
    @ResponseStatus(HttpStatus.OK)
    public void savePaymentMethod(@RequestBody PaymentMethodData paymentMethodData) {
        PaymentMethod paymentMethod = paymentMethodData.getPaymentMethod();
        PaymentType paymentType = paymentMethodData.getPaymentType();
        paymentType.setPaymentMethod(paymentMethod);
        paymentMethod.setPaymentType(paymentType);
        paymentMethodService.save(paymentMethod);
    }

    @DeleteMapping("/admin/paymentMethod/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePaymentMethod(@PathVariable Long id) {
        paymentMethodService.deleteById(id);
    }

}
