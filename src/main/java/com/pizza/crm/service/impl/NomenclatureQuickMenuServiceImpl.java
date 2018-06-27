package com.pizza.crm.service.impl;

import com.pizza.crm.model.NomenclatureQuickMenu;
import com.pizza.crm.repository.DishQuickMenuRepository;
import com.pizza.crm.service.NomenclatureQuickMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class NomenclatureQuickMenuServiceImpl implements NomenclatureQuickMenuService {


    private final DishQuickMenuRepository dishQuickMenuRepository;

    @Autowired
    public NomenclatureQuickMenuServiceImpl(DishQuickMenuRepository dishQuickMenuRepository) {
        this.dishQuickMenuRepository = dishQuickMenuRepository;
    }

    @Override
    public Collection<NomenclatureQuickMenu> getAll() {
        return dishQuickMenuRepository.findAll();
    }

    @Override
    public Optional<NomenclatureQuickMenu> findById(Long id) {
        return dishQuickMenuRepository.findById(id);
    }

    @Override
    public NomenclatureQuickMenu save(NomenclatureQuickMenu dish) {
        return dishQuickMenuRepository.save(dish);
    }

    @Override
    public Collection<NomenclatureQuickMenu> saveAll(Collection<NomenclatureQuickMenu> nomenclatureQuickMenus) {
        return dishQuickMenuRepository.saveAll(nomenclatureQuickMenus);
    }

    @Override
    public void deleteById(Long id) {
        dishQuickMenuRepository.deleteById(id);
    }

}
