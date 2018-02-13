package com.pizza.crm.repository;

import com.pizza.crm.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriesRepository extends CrudRepository<Category, Long> {

    @Override
    List<Category> findAll();

    @Query("SELECT u FROM Category u WHERE u.name = :name")
    Category getCategoriesByNameRepository(@Param("name") String name);

}
