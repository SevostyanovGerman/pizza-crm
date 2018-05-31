package com.pizza.crm.controller;

import com.pizza.crm.model.PaymentType;
import com.pizza.crm.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by User on 30.05.2018.
 */
@RestController
public class CashBoxControllerRest {

    private final PaymentTypeService paymentTypeService;

    @Autowired
    public CashBoxControllerRest(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @PostMapping("/getAllPaymentTypesForCashbox")
    public Collection<PaymentType> getAllPaymentTypesForCashbox(){
        return paymentTypeService.getAll();
    }
}
