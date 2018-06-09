package com.pizza.crm.service;

import com.pizza.crm.model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getPayments();

    void save(Payment payment);

}
