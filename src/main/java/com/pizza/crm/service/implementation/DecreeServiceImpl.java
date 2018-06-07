package com.pizza.crm.service.implementation;

import com.pizza.crm.model.Decree;
import com.pizza.crm.repository.DecreeRepository;
import com.pizza.crm.service.DecreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DecreeServiceImpl implements DecreeService {

    private final DecreeRepository decreeRepository;

    @Autowired
    public DecreeServiceImpl(DecreeRepository decreeRepository) {
        this.decreeRepository = decreeRepository;
    }

    @Override
    public Collection<Decree> getAll() {
        return decreeRepository.findAll();
    }

    @Override
    public Optional<Decree> findById(Long id) {
        return decreeRepository.findById(id);
    }

    @Override
    public Decree save(Decree decree) {
        return decreeRepository.save(decree);
    }

    @Override
    public Collection<Decree> saveAll(Collection<Decree> decree) {
        return decreeRepository.saveAll(decree);
    }

    @Override
    public void deleteById(Long id) {
        decreeRepository.deleteById(id);
    }
}
