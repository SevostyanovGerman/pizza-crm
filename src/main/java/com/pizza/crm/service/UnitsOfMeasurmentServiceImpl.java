package com.pizza.crm.service;

import com.pizza.crm.repository.UnitsOfMeasurmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UnitsOfMeasurmentImpl implements UnitsOfMeasurment{


    @Autowired
    UnitsOfMeasurmentRepository measurmentRepository;


    @Override
    public List<UnitsOfMeasurment> getAllUnits() {
        return measurmentRepository.findAll();
    }

    @Override
    public UnitsOfMeasurment getUnitById(int id) {
        return null;
    }

    @Override
    public UnitsOfMeasurment getUnitByName(String name) {
        return null;
    }

    @Override
    public void addUnit(UnitsOfMeasurment unit) {

    }

    @Override
    public void updateUnit(UnitsOfMeasurment unit) {

    }

    @Override
    public void deleteUnit(int id) {

    }
}
