package com.pizza.crm.repository;

import com.pizza.crm.model.Validity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ValidityRepository extends JpaRepository<Validity, Long> {

    Validity findByNameValidity(String name);

    void deleteByNameValidity(String name);
}
