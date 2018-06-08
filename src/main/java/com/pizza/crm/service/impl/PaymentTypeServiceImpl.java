package com.pizza.crm.service.impl;

import com.pizza.crm.model.PaymentType;
import com.pizza.crm.repository.PaymentTypeRepository;
import com.pizza.crm.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;

    @Autowired
    public PaymentTypeServiceImpl(PaymentTypeRepository paymentTypeRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
    }

    @Override
    public Collection<PaymentType> getAll() {
        return paymentTypeRepository.findAll();
    }

    @Override
    public Optional<PaymentType> findById(Long id) {
        return paymentTypeRepository.findById(id);
    }
    @Override
    public Optional<PaymentType> findByName(String name) {
        return paymentTypeRepository.findByName(name);
    }

    @Override
    public PaymentType save(PaymentType paymentType) {
        return paymentTypeRepository.save(paymentType);
    }

    @Override
    public Collection<PaymentType> saveAll(Collection<PaymentType> paymentTypes) {
        return paymentTypeRepository.saveAll(paymentTypes);
    }

    @Override
    public void deleteById(Long id) {
        paymentTypeRepository.deleteById(id);
    }
}
