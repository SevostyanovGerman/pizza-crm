package com.pizza.crm.repository;

import com.pizza.crm.model.Category;
import com.pizza.crm.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount getByName(String name);

    Discount getById(Long id);

    List<Discount> findAll();

    void deleteByName(String name);

    void deleteById(Long id);

    @Query("SELECT u FROM Discount u WHERE u.active = :active AND u.setAuto = :setAuto")
    List<Discount> getActiveDiscount(@Param("active") boolean active, @Param("setAuto") boolean setAuto);
}
