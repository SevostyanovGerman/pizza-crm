package com.pizza.crm.service;

import com.pizza.crm.model.discount.Discount;

import java.util.List;

public interface DiscountService extends CrudService<Discount, Long> {
    List<Discount> getEnabledDiscounts();

    Discount findByName(String name);
}
