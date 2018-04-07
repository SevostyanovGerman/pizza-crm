package com.pizza.crm.service;

import com.pizza.crm.model.UnitsOfMeasurement;
import com.pizza.crm.repository.UnitsOfMeasurmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UnitsOfMeasurmentServiceImpl implements UnitsOfMeasurmentService {


    private final UnitsOfMeasurmentRepository measurmentRepository;

    @Autowired
    public UnitsOfMeasurmentServiceImpl(UnitsOfMeasurmentRepository measurmentRepository) {
        this.measurmentRepository = measurmentRepository;
    }

    @Override
    public Collection<UnitsOfMeasurement> getAll() {
        return measurmentRepository.findAll();
    }

    @Override
    public Optional<UnitsOfMeasurement> findById(Long aLong) {
        return Optional.empty();
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
}
