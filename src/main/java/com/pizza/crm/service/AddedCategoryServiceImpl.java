package com.pizza.crm.service;

import com.pizza.crm.model.AddedCategory;
import com.pizza.crm.repository.AddedCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddedCategoryServiceImpl implements AddedCategoryService {

    @Autowired
    private AddedCategoryDao addedCategoryDao;

    @Override
    public void save(AddedCategory category) {
        addedCategoryDao.save(category);
    }

    @Override
    public void delete(Long id) {
        addedCategoryDao.delete(id);
    }

    @Override
    public AddedCategory getCategory(Long id) {
        return addedCategoryDao.getCategory(id);
    }

    @Override
    public AddedCategory getCategoryByName(String name) {
        return addedCategoryDao.getCategoryByName(name);
    }

    @Override
    public List<AddedCategory> getAllCategories() {
        return addedCategoryDao.getAllCategories();
    }
}
