package com.pizza.crm.service;

import com.pizza.crm.model.discount.Discount;

import java.util.List;

public interface DiscountService extends CrudService<Discount, Long> {

    List<Discount> getActiveDiscount(boolean active, boolean manualInput, int minSum);

}
