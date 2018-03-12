package com.pizza.crm.repository;

import com.pizza.crm.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount getByName(String name);

    Discount getById(Long id);

    List<Discount> findAll();

    @Transactional
    void deleteByName(String name);

    @Transactional
    void deleteById(Long id);
}
