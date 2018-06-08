package com.pizza.crm.service.impl;

import com.pizza.crm.model.Nomenclature;
import com.pizza.crm.repository.NomenclatureRepository;
import com.pizza.crm.service.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class NomenclatureServiceImpl implements NomenclatureService {

    private final NomenclatureRepository nomenclatureRepository;

    @Autowired
    public NomenclatureServiceImpl(NomenclatureRepository nomenclatureRepository) {
        this.nomenclatureRepository = nomenclatureRepository;
    }

    @Override
    public void save(Nomenclature nomenclature) {
        nomenclatureRepository.save(nomenclature);
    }

    @Override
    public void delete(Long id) {
        nomenclatureRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        nomenclatureRepository.deleteByName(name);
    }

    @Override
    public Nomenclature getNomenclature(Long id) {
        return nomenclatureRepository.getOne(id);
    }

    @Override
    public Nomenclature getNomenclatureByName(String name) {
        return nomenclatureRepository.getByName(name);
    }

    @Override
    public List<Nomenclature> findAllNomenclatures() {
        return nomenclatureRepository.findAll();
    }

    @Override
    public Set<Nomenclature> getModifierNomenclatureDish() {
        return nomenclatureRepository.getModifierNomenclatureDish();
    }

    @Override
    public Set<Nomenclature> getNomenclatureModifiers() {
        return nomenclatureRepository.getNomenclatureModifiers();
    }

    @Override
    public List<Nomenclature> getNomenclaturesWithoutParentGroup() {
        return nomenclatureRepository.getNomenclaturesWithoutParentGroup();
    }
}
