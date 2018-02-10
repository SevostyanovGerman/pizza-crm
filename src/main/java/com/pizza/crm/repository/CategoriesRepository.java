package com.pizza.crm.repository;

import com.pizza.crm.model.Categories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriesRepository extends CrudRepository<Categories, Long> {

    @Override
    List<Categories> findAll();

    @Query("SELECT u FROM Categories u WHERE u.name = :name")
    Categories getCategoriesByNameRepository(@Param("name") String name);

}
