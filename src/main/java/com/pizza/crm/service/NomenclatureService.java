package com.pizza.crm.service;

import com.pizza.crm.model.Nomenclature;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface NomenclatureService {

    void save(Nomenclature nomenclature);

    void delete(Long id);

    void deleteByName(String name);

    Nomenclature getNomenclature(Long id);

    Nomenclature getNomenclatureByName(String name);

    List<Nomenclature> findAllNomenclatures();

    Set<Nomenclature> getModifierNomenclatureDish();

    Set<Nomenclature> getNomenclatureModifiers();

    List<Nomenclature> getNomenclaturesWithoutParentGroup();
}
