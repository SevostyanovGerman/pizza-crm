package com.pizza.crm.repository;

import com.pizza.crm.model.AbstractEntity;
import com.pizza.crm.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface OrderRepository<T extends AbstractEntity> extends JpaRepository<T, Long> {
}
