package com.pizza.crm.service;

import com.pizza.crm.model.UnitsOfMeasurement;
import com.pizza.crm.repository.UnitsOfMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UnitsOfMeasurementServiceImpl implements UnitsOfMeasurementService {


    private final UnitsOfMeasurementRepository measurmentRepository;

    @Autowired
    public UnitsOfMeasurementServiceImpl(UnitsOfMeasurementRepository measurmentRepository) {
        this.measurmentRepository = measurmentRepository;
    }

    @Override
    public Collection<UnitsOfMeasurement> getAll() {
        return measurmentRepository.findAll();
    }

    @Override
    public Optional<UnitsOfMeasurement> findById(Long aLong) {
        return measurmentRepository.findById(aLong);
    }

    @Override
    public UnitsOfMeasurement save(UnitsOfMeasurement unitsOfMeasurement) {
        return measurmentRepository.save(unitsOfMeasurement);
    }

    @Override
    public Collection<UnitsOfMeasurement> saveAll(Collection<UnitsOfMeasurement> unitsOfMeasurements) {
        return measurmentRepository.saveAll(unitsOfMeasurements);
    }

    @Override
    public void deleteById(Long id) {
        measurmentRepository.deleteById(id);
    }

    @Override
    public UnitsOfMeasurement findByName(String name) {
        return measurmentRepository.findByName(name);
    }
}
