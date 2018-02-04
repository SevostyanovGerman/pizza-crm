package com.pizza.crm.repository;

import com.pizza.crm.model.Categories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriesRepository extends CrudRepository<Categories, Long> {

    @Override
    List<Categories> findAll();


}
