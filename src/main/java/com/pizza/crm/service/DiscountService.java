package com.pizza.crm.service;

import com.pizza.crm.model.Discount;

import java.util.List;

public interface DiscountService {

    Long save(Discount discount);

    void delete(Discount discount);

    void deleteByName(String name);

    void deleteById(Long id);

    Discount getByName(String name);

    Discount getById(Long id);

    List<Discount> findAll();

    List<Discount> getActiveDiscount(boolean active, boolean acceptManualDiscount);

}
