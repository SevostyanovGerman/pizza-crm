package com.pizza.crm.service.impl;

import com.pizza.crm.model.AddedCategory;
import com.pizza.crm.repository.AddedCategoryRepository;
import com.pizza.crm.service.AddedCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddedCategoryServiceImpl implements AddedCategoryService {

    private final AddedCategoryRepository addedCategoryRepository;

    @Autowired
    public AddedCategoryServiceImpl(AddedCategoryRepository addedCategoryRepository) {
        this.addedCategoryRepository = addedCategoryRepository;
    }

    @Override
    public void save(AddedCategory category) {
        addedCategoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        addedCategoryRepository.deleteById(id);
    }

    @Override
    public AddedCategory getCategory(Long id) {
        return addedCategoryRepository.findById(id).get();
    }

    @Override
    public AddedCategory getCategoryByName(String name) {
        return addedCategoryRepository.findAddedCategoryByName(name);
    }

    public List<AddedCategory> findAllCategories() {
        return addedCategoryRepository.findAll();
    }
}
