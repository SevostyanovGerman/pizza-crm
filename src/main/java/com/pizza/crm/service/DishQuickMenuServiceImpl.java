package com.pizza.crm.service;

import com.pizza.crm.model.DishQuickMenu;
import com.pizza.crm.repository.DishQuickMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DishQuickMenuServiceImpl implements DishQuickMenuService {


    private final DishQuickMenuRepository dishQuickMenuRepository;

    @Autowired
    public DishQuickMenuServiceImpl(DishQuickMenuRepository dishQuickMenuRepository) {
        this.dishQuickMenuRepository = dishQuickMenuRepository;
    }

    @Override
    public Collection<DishQuickMenu> getAll() {
        return dishQuickMenuRepository.findAll();
    }

    @Override
    public Optional<DishQuickMenu> findById(Long id) {
        return dishQuickMenuRepository.findById(id);
    }

    @Override
    public DishQuickMenu save(DishQuickMenu dish) {
        return dishQuickMenuRepository.save(dish);
    }

    @Override
    public Collection<DishQuickMenu> saveAll(Collection<DishQuickMenu> dishQuickMenu) {
        return dishQuickMenuRepository.saveAll(dishQuickMenu);
    }

    @Override
    public void deleteById(Long id) {
        dishQuickMenuRepository.deleteById(id);
    }

}
