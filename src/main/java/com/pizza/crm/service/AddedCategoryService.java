package com.pizza.crm.service;

import com.pizza.crm.model.AddedCategory;

import java.util.List;

public interface AddedCategoryService {

    void save(AddedCategory category);

    void delete(Long id);

    AddedCategory getCategory(Long id);

    List<AddedCategory> getAllCategories();

}
