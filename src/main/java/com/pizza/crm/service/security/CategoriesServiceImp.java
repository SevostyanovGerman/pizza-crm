package com.pizza.crm.service.security;

import com.pizza.crm.model.Categories;
import com.pizza.crm.model.security.Role;
import com.pizza.crm.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CategoriesServiceImp implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Collection<Categories> getAll() {
        return categoriesRepository.findAll();
    }

    @Override
    public Optional<Categories> findById(Long id) {
        return categoriesRepository.findById(id);
    }

    @Override
    public Categories save(Categories categories) {
        return categoriesRepository.save(categories);
    }

    @Override
    public void deleteById(Long id) {
        categoriesRepository.deleteById(id);
    }
}
