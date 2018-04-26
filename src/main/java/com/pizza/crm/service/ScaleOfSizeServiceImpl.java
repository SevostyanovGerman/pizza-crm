package com.pizza.crm.service;

import com.pizza.crm.model.UnitsOfMeasurement;
import com.pizza.crm.repository.ScaleOfSizeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ScaleOfSizeServiceImpl implements ScaleOfSizeService {

    private final ScaleOfSizeRepository scaleOfSizeRepository;

    public ScaleOfSizeServiceImpl(ScaleOfSizeRepository scaleOfSizeRepository) {
        this.scaleOfSizeRepository = scaleOfSizeRepository;
    }

    @Override
    public Collection<UnitsOfMeasurement> getAll() {
        return scaleOfSizeRepository.findAll();
    }

    @Override
    public Optional<UnitsOfMeasurement> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public UnitsOfMeasurement save(UnitsOfMeasurement unitsOfMeasurement) {
        return null;
    }

    @Override
    public Collection<UnitsOfMeasurement> saveAll(Collection<UnitsOfMeasurement> unitsOfMeasurements) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
