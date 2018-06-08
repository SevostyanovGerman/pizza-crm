package com.pizza.crm.service.impl;

import com.pizza.crm.model.ScaleOfSize;
import com.pizza.crm.repository.ScaleOfSizeRepository;
import com.pizza.crm.service.ScaleOfSizeService;
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
    public Collection<ScaleOfSize> getAll() {
        return scaleOfSizeRepository.findAll();
    }

    @Override
    public Optional<ScaleOfSize> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public ScaleOfSize save(ScaleOfSize scaleOfSize) {
        return scaleOfSizeRepository.save(scaleOfSize);
    }

    @Override
    public Collection<ScaleOfSize> saveAll(Collection<ScaleOfSize> scaleOfSizes) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        scaleOfSizeRepository.deleteById(id);
    }

    @Override
    public ScaleOfSize findByNameScale(String name) {
        return scaleOfSizeRepository.findByNameScale(name);
    }

    @Override
    public void deleteByNameScale(String name) {
        scaleOfSizeRepository.deleteByNameScale(name);
    }

}
