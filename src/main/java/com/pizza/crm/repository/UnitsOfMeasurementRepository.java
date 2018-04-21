package com.pizza.crm.repository;

import com.pizza.crm.model.UnitsOfMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UnitsOfMeasurementRepository extends JpaRepository<UnitsOfMeasurement, Long> {

    UnitsOfMeasurement findByName(String name);
}
