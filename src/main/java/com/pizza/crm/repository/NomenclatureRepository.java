package com.pizza.crm.repository;

import com.pizza.crm.model.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface NomenclatureRepository extends JpaRepository<Nomenclature, Long> {

    Nomenclature getByName(String name);

    List<Nomenclature> findAll();

}
