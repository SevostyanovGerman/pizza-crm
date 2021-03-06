package com.pizza.crm.repository;

import com.pizza.crm.model.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
public interface NomenclatureRepository extends JpaRepository<Nomenclature, Long> {

    Nomenclature getByName(String name);

    List<Nomenclature> findAll();

    void deleteByName(String name);

    @Query("SELECT n FROM Nomenclature n WHERE n.nomenclatureType = com.pizza.crm.model.NomenclatureType.DISH")
    Set<Nomenclature> getModifierNomenclatureDish();

    @Query("SELECT m FROM NomenclatureParentGroup m INNER JOIN m.nomenclatures r WHERE r.nomenclatureType = 'MODIFIER'")
    Set<Nomenclature> getNomenclatureModifiers();

    @Query("SELECT n FROM Nomenclature n WHERE n.nomenclatureParentGroupSet is empty")
    List<Nomenclature> getNomenclaturesWithoutParentGroup();
}
