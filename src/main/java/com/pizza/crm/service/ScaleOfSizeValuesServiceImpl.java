package com.pizza.crm.service;


import com.pizza.crm.model.ScaleOfSizeValues;
import com.pizza.crm.repository.ScaleOfSizeValuesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ScaleOfSizeValuesServiceImpl implements ScaleOfSizeValuesService{

    private final ScaleOfSizeValuesRepository scaleOfSizeValuesRepository;

    public ScaleOfSizeValuesServiceImpl(ScaleOfSizeValuesRepository scaleOfSizeValuesRepository) {
        this.scaleOfSizeValuesRepository = scaleOfSizeValuesRepository;
    }

    @Override
    public Collection<ScaleOfSizeValues> getAll() {
        return scaleOfSizeValuesRepository.findAll();
    }

    @Override
    public Optional<ScaleOfSizeValues> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public ScaleOfSizeValues save(ScaleOfSizeValues scaleOfSizeValues) {
        return scaleOfSizeValuesRepository.save(scaleOfSizeValues);
    }

    @Override
    public Collection<ScaleOfSizeValues> saveAll(Collection<ScaleOfSizeValues> scaleOfSizeValues) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public ScaleOfSizeValues findByNameSize(String name) {
        return scaleOfSizeValuesRepository.findByNameSize(name);
    }

    @Override
    public void deleteByNameSize(String name) {
        scaleOfSizeValuesRepository.deleteByNameSize(name);
    }

}
