package com.pizza.crm.repository;

import com.pizza.crm.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT u FROM Category u WHERE u.name = :name")
    Category getCategoriesByNameRepository(@Param("name") String name);

}
