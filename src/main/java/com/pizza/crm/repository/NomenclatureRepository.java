package com.pizza.crm.repository;

import com.pizza.crm.model.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface NomenclatureRepository extends JpaRepository<Nomenclature, Long> {

    Nomenclature getByName(String name);

    List<Nomenclature> findAll();

    @Query("SELECT n FROM Nomenclature n WHERE n.nomenclatureType = com.pizza.crm.model.NomenclatureType.MODIFIER")
    List<Nomenclature> getNomenclaturesModifiers();

    @Query("SELECT n FROM Nomenclature n WHERE n.nomenclatureParentGroupSet is empty")
    List<Nomenclature> getNomenclaturesWithoutParentGroup();

}
