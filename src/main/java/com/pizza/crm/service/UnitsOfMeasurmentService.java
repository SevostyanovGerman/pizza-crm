package com.pizza.crm.service;

import java.util.List;

public interface UnitsOfMeasurment {

    List<UnitsOfMeasurment> getAllUnits();
    UnitsOfMeasurment getUnitById(int id);
    UnitsOfMeasurment getUnitByName(String name);
    void addUnit(UnitsOfMeasurment unit);
    void updateUnit(UnitsOfMeasurment unit);
    void deleteUnit(int id);
}

