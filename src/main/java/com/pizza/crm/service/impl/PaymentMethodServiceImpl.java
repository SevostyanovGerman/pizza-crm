package com.pizza.crm.service.impl;

import com.pizza.crm.model.PaymentMethod;
import com.pizza.crm.repository.PaymentMethodRepository;
import com.pizza.crm.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public Collection<PaymentMethod> getAll() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public Optional<PaymentMethod> findById(Long id) {
        return paymentMethodRepository.findById(id);
    }

    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public Collection<PaymentMethod> saveAll(Collection<PaymentMethod> paymentMethods) {
        return paymentMethodRepository.saveAll(paymentMethods);
    }

    @Override
    public void deleteById(Long id) {
        paymentMethodRepository.deleteById(id);
    }

    @Override
    public PaymentMethod getPaymentMethodByName(String name) {
        return paymentMethodRepository.getPaymentMethodByName(name);
    }
}
