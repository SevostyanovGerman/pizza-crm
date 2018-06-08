package com.pizza.crm.repository;

import com.pizza.crm.model.ScaleOfSize;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ScaleOfSizeRepository extends JpaRepository<ScaleOfSize, Long> {

    //название метода должно быть findByИмяПоляВМодели
    ScaleOfSize findByNameScale(String name);
    void deleteByNameScale(String name);
}
