package com.pizza.crm.service;

import com.pizza.crm.model.ScaleOfSize;
import com.pizza.crm.model.ScaleOfSizeValues;

public interface ScaleOfSizeService extends CrudService<ScaleOfSize, Long>{

    ScaleOfSize findByNameScale(String name);
    void deleteByNameScale(String name);
}
