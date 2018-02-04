package com.pizza.crm.repository;

import com.pizza.crm.model.Dish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DishRepository extends CrudRepository<Dish, Long> {

    @Override
    List<Dish> findAll();
}
