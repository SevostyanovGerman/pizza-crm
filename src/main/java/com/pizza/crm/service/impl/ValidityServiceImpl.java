package com.pizza.crm.service.impl;

import com.pizza.crm.model.Validity;
import com.pizza.crm.repository.ValidityRepository;
import com.pizza.crm.service.ValidityService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ValidityServiceImpl implements ValidityService {

    private final ValidityRepository validityRepository;

    public ValidityServiceImpl(ValidityRepository validityRepository) {
        this.validityRepository = validityRepository;
    }

    @Override
    public Collection<Validity> getAll() {
        return validityRepository.findAll();
    }

    @Override
    public Optional<Validity> findById(Long id) {
        return validityRepository.findById(id);
    }

    @Override
    public Validity save(Validity validity) {
        return validityRepository.save(validity);
    }

    @Override
    public Collection<Validity> saveAll(Collection<Validity> validities) {
        return validityRepository.saveAll(validities);
    }

    @Override
    public void deleteById(Long id) {
        validityRepository.deleteById(id);
    }

    @Override
    public Validity findByNameValidity(String name) {
        return validityRepository.findByNameValidity(name);
    }

    @Override
    public void deleteByNameValidity(String name) {
        validityRepository.deleteByNameValidity(name);
    }
}
