package com.pizza.crm.service.implementation;

import com.pizza.crm.model.Category;
import com.pizza.crm.model.Dish;
import com.pizza.crm.repository.CategoryRepository;
import com.pizza.crm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Collection<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoriesByNameRepository(name);
    }

    @Override
    public void updateCategoriesName(Category category) {
        Category categoryDb = categoryRepository.findById(category.getId()).get();
        categoryDb.setName(category.getName());
        categoryRepository.save(categoryDb);
    }

    @Override
    public Collection<Category> saveAll(Collection<Category> dishCategories) {
        return categoryRepository.saveAll(dishCategories);
    }

    @Override
    public void updateCategoriesDish(Long id, Set<Dish> dish) {
        Category categoryDb = categoryRepository.findById(id).get();
        categoryDb.setDishes(dish);
        categoryRepository.save(categoryDb);
    }
}
