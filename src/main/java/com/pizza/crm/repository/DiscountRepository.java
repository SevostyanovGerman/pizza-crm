package com.pizza.crm.repository;

import com.pizza.crm.model.discount.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("SELECT d FROM Discount d WHERE d.enabled = true")
    List<Discount> getEnabledDiscounts();

    Discount findByName(String name);

    @Query("SELECT d FROM Discount d WHERE d.name = :discounts")
    List<Discount> getDiscountsForOrder(@Param("discounts") List<Discount> discounts);
}
