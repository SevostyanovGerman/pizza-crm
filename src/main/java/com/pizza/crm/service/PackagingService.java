package com.pizza.crm.service;

import com.pizza.crm.model.Packaging;

import java.util.List;

public interface PackagingService {

    void save(Packaging packaging);

    void delete(Long id);

    Packaging getNomenclature(Long id);

    Packaging getNomenclatureByName(String name);

    List<Packaging> findAllPackagings();

}
