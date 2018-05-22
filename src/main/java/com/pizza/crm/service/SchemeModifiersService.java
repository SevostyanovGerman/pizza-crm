package com.pizza.crm.service;

import com.pizza.crm.model.SchemeModifiers;

import java.util.List;

public interface SchemeModifiersService {

    void delete(String name);

    SchemeModifiers getByName(String name);

    void save(SchemeModifiers schemeModifiers);

    List<SchemeModifiers> findAll();
}
