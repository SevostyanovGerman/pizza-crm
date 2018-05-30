package com.pizza.crm.service;

import com.pizza.crm.model.Nomenclature;

import java.util.HashSet;
import java.util.List;

public interface NomenclatureService {

    void save(Nomenclature nomenclature);

    void delete(Long id);

    void deleteByName(String name);

    Nomenclature getNomenclature(Long id);

    Nomenclature getNomenclatureByName(String name);

    List<Nomenclature> findAllNomenclatures();

    HashSet<Nomenclature> getModifierNomenclatureDish();

    HashSet<Nomenclature> getNomenclatureModifiers();

    List<Nomenclature> getNomenclaturesWithoutParentGroup();
}
