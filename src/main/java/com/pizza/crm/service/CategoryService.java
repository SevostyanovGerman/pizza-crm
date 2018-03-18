package com.pizza.crm.service;

import com.pizza.crm.model.Category;
import com.pizza.crm.model.Dish;

import java.util.Set;

public interface CategoryService extends CrudService<Category, Long> {

    void updateCategoriesName(Category category);

    void updateCategoriesDish(Long id, Set<Dish> dish);

    Category getCategoryByName(String name);

}
