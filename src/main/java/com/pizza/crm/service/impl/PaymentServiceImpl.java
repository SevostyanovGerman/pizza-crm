package com.pizza.crm.service.impl;

import com.pizza.crm.model.Payment;
import com.pizza.crm.repository.PaymentRepository;
import com.pizza.crm.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }
}
