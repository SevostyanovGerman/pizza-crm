package com.pizza.crm.service.implementation;

import com.pizza.crm.model.SchemeModifiers;
import com.pizza.crm.repository.SchemeModifiersRepository;
import com.pizza.crm.service.SchemeModifiersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemeModifierServiceImpl implements SchemeModifiersService {

    private SchemeModifiersRepository schemeModifiersRepository;

    @Autowired
    public SchemeModifierServiceImpl(SchemeModifiersRepository schemeModifiersRepository) {
        this.schemeModifiersRepository = schemeModifiersRepository;
    }

    @Override
    public void delete(String name) {
        schemeModifiersRepository.deleteByName(name);
    }

    @Override
    public SchemeModifiers getByName(String name) {
        return schemeModifiersRepository.getByName(name);
    }

    @Override
    public void save(SchemeModifiers schemeModifiers) {
        schemeModifiersRepository.save(schemeModifiers);
    }

    @Override
    public List<SchemeModifiers> findAll() {
        return schemeModifiersRepository.findAll();
    }
}
