package com.pizza.crm.repository;

import com.pizza.crm.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    PaymentMethod getPaymentMethodByName(String name);
}
