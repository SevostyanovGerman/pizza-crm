package com.pizza.crm.service;

import com.pizza.crm.model.ScaleOfSizeValues;

public interface ScaleOfSizeValuesService extends CrudService<ScaleOfSizeValues, Long> {

    ScaleOfSizeValues findByNameSize(String name);
    void deleteByNameSize(String name);
}
