package com.pizza.crm.repository;

import com.pizza.crm.model.Dish;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {

    @Override
    List<Dish> findAll();

    Dish getDishByName(String name);

    @Query("SELECT dish FROM Dish dish WHERE (dish.name IN :dishNames)")
    List<Dish> getDishesByName(@Param("dishNames") List<String> dishNames);

}
