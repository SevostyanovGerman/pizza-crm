package com.pizza.crm.repository;

import com.pizza.crm.model.AddedCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface AddedCategoryDao {

    void save(AddedCategory category);

    void delete(Long id);

    AddedCategory getCategory(Long id);

    AddedCategory getCategoryByName(String name);

    List<AddedCategory> getAllCategories();

}
