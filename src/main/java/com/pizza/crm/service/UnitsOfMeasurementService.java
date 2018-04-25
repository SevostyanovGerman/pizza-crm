package com.pizza.crm.service;

import com.pizza.crm.model.UnitsOfMeasurement;

public interface UnitsOfMeasurementService extends CrudService<UnitsOfMeasurement, Long> {

    UnitsOfMeasurement findByName(String name);
}

