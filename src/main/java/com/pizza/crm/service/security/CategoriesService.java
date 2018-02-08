package com.pizza.crm.service.security;

import com.pizza.crm.model.Categories;
import com.pizza.crm.model.Dish;
import com.pizza.crm.service.CrudService;

import java.util.Set;

public interface CategoriesService extends CrudService<Categories, Long> {

    void updateCategoriesName(Categories categories);

    void updateCategoriesDish(long id, Set<Dish> dish);

}
