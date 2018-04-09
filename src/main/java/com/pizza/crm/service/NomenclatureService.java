package com.pizza.crm.service;

import com.pizza.crm.model.Nomenclature;

import java.util.List;

public interface NomenclatureService {

    void save(Nomenclature nomenclature);

    void delete(Long id);

    Nomenclature getNomenclature(Long id);

    Nomenclature getNomenclatureByName(String name);

    List<Nomenclature> findAllNomenclatures();

    List<Nomenclature> getNomenclatureModifiers();
}
