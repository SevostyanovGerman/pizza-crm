package com.pizza.crm.service;

import com.pizza.crm.model.AddedCategory;
import com.pizza.crm.repository.AddedCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddedCategoryServiceImpl implements AddedCategoryService {
    @Autowired
    AddedCategoryRepository repository;

    @Override
    public void save(AddedCategory category) {
        repository.save(category);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AddedCategory getCategory(Long id) {
        return repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Category not found"));
    }

    @Override
    public List<AddedCategory> getAllCategories() {
        List<AddedCategory> categories = new ArrayList<>();
        repository.findAll().forEach(category -> categories.add(category));
        return categories;
    }
}
