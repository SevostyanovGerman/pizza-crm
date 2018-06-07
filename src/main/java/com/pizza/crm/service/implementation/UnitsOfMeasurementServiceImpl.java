package com.pizza.crm.service.implementation;

import com.pizza.crm.model.UnitsOfMeasurement;
import com.pizza.crm.repository.UnitsOfMeasurementRepository;
import com.pizza.crm.service.UnitsOfMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UnitsOfMeasurementServiceImpl implements UnitsOfMeasurementService {


    private final UnitsOfMeasurementRepository measurementRepository;

    @Autowired
    public UnitsOfMeasurementServiceImpl(UnitsOfMeasurementRepository measurmentRepository) {
        this.measurementRepository = measurmentRepository;
    }

    @Override
    public Collection<UnitsOfMeasurement> getAll() {
        return measurementRepository.findAll();
    }

    @Override
    public Optional<UnitsOfMeasurement> findById(Long aLong) {
        return measurementRepository.findById(aLong);
    }

    @Override
    public UnitsOfMeasurement save(UnitsOfMeasurement unitsOfMeasurement) {
        return measurementRepository.save(unitsOfMeasurement);
    }

    @Override
    public Collection<UnitsOfMeasurement> saveAll(Collection<UnitsOfMeasurement> unitsOfMeasurements) {
        return measurementRepository.saveAll(unitsOfMeasurements);
    }

    @Override
    public void deleteById(Long id) {
        measurementRepository.deleteById(id);
    }

    @Override
    public UnitsOfMeasurement findByName(String name) {
        return measurementRepository.findByName(name);
    }
}
