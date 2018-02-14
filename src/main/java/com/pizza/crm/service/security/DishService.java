package com.pizza.crm.service.security;

import com.pizza.crm.model.Category;
import com.pizza.crm.model.Dish;
import com.pizza.crm.service.CrudService;

import java.util.Collection;
import java.util.Set;

public interface DishService extends CrudService<Dish, Long> {

    Collection<Category> getAvailableCategories(Dish dish);

    void updateDishCategories(Dish dish);

}
