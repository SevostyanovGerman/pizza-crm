package com.pizza.crm.repository;

import com.pizza.crm.model.discount.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("SELECT u FROM Discount u WHERE u.enabled = :enabled AND u.manualInput = :manualInput AND u.minSum <= :minSum")
    List<Discount> getActiveDiscount(@Param("enabled") boolean enabled, @Param("manualInput") boolean manualInput, @Param("minSum") int minSum);
}
