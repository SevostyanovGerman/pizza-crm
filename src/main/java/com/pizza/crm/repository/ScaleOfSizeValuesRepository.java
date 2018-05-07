package com.pizza.crm.repository;

import com.pizza.crm.model.ScaleOfSizeValues;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScaleOfSizeValuesRepository extends JpaRepository<ScaleOfSizeValues, Long> {
    //название метода должно быть findByИмяПоляВМодели
    ScaleOfSizeValues findByNameSize(String name);

}
