package com.pizza.crm.service;

import com.pizza.crm.model.AbstractEntity;
import com.pizza.crm.model.Order;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BasicService<T extends AbstractEntity> {

    List<T> getAll();

    Optional<T> findById(Long aLong);

    void save(T invoice);

    List<T> saveAll(Collection<Order> orders);

    void deleteById(Long aLong);

    Optional<T> getBy(Object param);
}
