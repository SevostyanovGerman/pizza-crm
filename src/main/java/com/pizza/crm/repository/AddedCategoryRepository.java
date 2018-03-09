package com.pizza.crm.repository;

import com.pizza.crm.model.AddedCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddedCategoryRepository extends CrudRepository<AddedCategory, Long> {

    AddedCategory findAddedCategoryByName(String name);

    List<AddedCategory> findAll();

}
