package com.pizza.crm.service;

import com.pizza.crm.model.PaymentType;

import java.util.Optional;

public interface PaymentTypeService extends CrudService<PaymentType, Long> {
    Optional<PaymentType> findByName(String name);
}
