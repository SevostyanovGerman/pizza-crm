package com.pizza.crm.service;

import com.pizza.crm.model.Category;
import com.pizza.crm.model.Dish;
import com.pizza.crm.model.Ingredient;

import java.util.Collection;
import java.util.List;

public interface DishService extends CrudService<Dish, Long> {

    Collection<Category> getAvailableCategories(Dish dish);

    Collection<Ingredient> getAvailableIngredients(Dish dish);

    Dish getDishByName(String name);
}
