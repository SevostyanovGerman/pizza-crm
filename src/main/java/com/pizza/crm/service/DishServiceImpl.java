package com.pizza.crm.service;

import com.pizza.crm.model.Category;
import com.pizza.crm.model.Dish;
import com.pizza.crm.model.Ingredient;
import com.pizza.crm.repository.CategoryRepository;
import com.pizza.crm.repository.DishRepository;
import com.pizza.crm.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    private final CategoryRepository categoryRepository;

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository, CategoryRepository categoryRepository, IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
    }

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
    public Collection<Dish> saveAll(Collection<Dish> dishes) {
        return dishRepository.saveAll(dishes);
    }

    @Override
    public void deleteById(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public Collection<Category> getAvailableCategories(Dish dish) {
        Collection<Category> availableCategories = categoryRepository.findAll();
        availableCategories.removeAll(dish.getCategories());
        return availableCategories;
    }

    @Override
    public Collection<Ingredient> getAvailableIngredients(Dish dish) {
        Collection<Ingredient> availableIngredients = ingredientRepository.findAll();
        availableIngredients.removeAll(dish.getIngredients());
        return availableIngredients;
    }

}
