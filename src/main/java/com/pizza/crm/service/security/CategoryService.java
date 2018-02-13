package com.pizza.crm.service.security;

import com.pizza.crm.model.Category;
import com.pizza.crm.model.Dish;
import com.pizza.crm.service.CrudService;

import java.util.Set;

public interface CategoryService extends CrudService<Category, Long> {

    void updateCategoriesName(Category category);

    void updateCategoriesDish(long id, Set<Dish> dish);

    Category getCategoriesByName(String name);

}
