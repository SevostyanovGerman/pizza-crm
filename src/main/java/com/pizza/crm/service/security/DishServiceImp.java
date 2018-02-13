package com.pizza.crm.service.security;


import com.pizza.crm.model.Dish;
import com.pizza.crm.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DishServiceImp implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Override
    public Collection<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id);
    }

    @Override
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public void deleteById(Long id) {
        dishRepository.deleteById(id);
    }

    public Dish getDishByName(String name){
        return dishRepository.getDishByName(name);
    }
}
