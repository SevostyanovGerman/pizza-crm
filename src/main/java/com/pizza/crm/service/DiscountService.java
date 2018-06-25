package com.pizza.crm.service;

import com.pizza.crm.model.discount.Discount;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public interface DiscountService extends CrudService<Discount, Long> {
    List<Discount> getEnabledDiscounts();

    Discount findByName(String name);

    List<Discount> getDiscountsForOrder(List<String> strings, DayOfWeek dayOfWeek,
                                        LocalDateTime localDateTime, Double total, Integer size);
}
