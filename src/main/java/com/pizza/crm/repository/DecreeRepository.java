package com.pizza.crm.repository;

import com.pizza.crm.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount getByName(String name);

    Discount getById(Long id);

    List<Discount> findAll();

    void deleteByName(String name);

    void deleteById(Long id);
}
