package com.pizza.crm.repository;

import com.pizza.crm.model.Packaging;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PackagingRepository extends JpaRepository<Packaging, Long> {

    Packaging getByName(String name);

    List<Packaging> findAll();
}
