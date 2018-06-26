package com.pizza.crm.repository;

import com.pizza.crm.model.ScaleOfSizeValues;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ScaleOfSizeValuesRepository extends JpaRepository<ScaleOfSizeValues, Long> {

    ScaleOfSizeValues findByNameSize(String name);

    void deleteByNameSize(String name);
}
