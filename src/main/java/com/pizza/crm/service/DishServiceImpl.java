package com.pizza.crm.service;

import com.pizza.crm.dto.DishForm;
import com.pizza.crm.model.Category;
import com.pizza.crm.model.Dish;
import com.pizza.crm.repository.CategoryRepository;
import com.pizza.crm.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
    public void save(DishForm dishForm) {
        save(from(dishForm));
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

    private Dish from(DishForm dishForm) {
        Dish dish = new Dish("");
        dish.setId(dishForm.getId());
        dish.setName(dishForm.getName());
        categoryRepository.findAllById(dishForm.getCategories()).forEach(dish::addDishCategory);
        return dish;
    }

}
