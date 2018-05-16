package com.pizza.crm.service;

import com.pizza.crm.model.Validity;

public interface ValidityService extends CrudService<Validity, Long>{

    Validity findByNameValidity(String name);
}
