package com.pizza.crm.repository;

import com.pizza.crm.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select max(o.id) from Order o")
    Long getLastRecordId();
}
