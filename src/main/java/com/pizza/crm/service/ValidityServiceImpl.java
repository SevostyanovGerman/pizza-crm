package com.pizza.crm.service;

import com.pizza.crm.model.Validity;
import com.pizza.crm.repository.ValidityRepository;
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
    public Optional<Validity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Validity save(Validity validity) {
        return validityRepository.save(validity);
    }

    @Override
    public Collection<Validity> saveAll(Collection<Validity> validities) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

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
