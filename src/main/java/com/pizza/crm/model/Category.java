package com.pizza.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
	@Column(name = "name", unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "Dish_Category",
            joinColumns = @JoinColumn(name = "category"),
            inverseJoinColumns = @JoinColumn(name = "dish"))
    @JsonBackReference
    private Set<Dish> dishes;

    public Category(String name, Set<Dish> dishes) {
        this.name = name;
        this.dishes = dishes;
    }

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Dish> getDishes() {
        Comparator<Dish> comp = (Dish o1, Dish o2) -> (o1.getName().compareTo(o2.getName()));
        Set dish = new TreeSet(comp);
        dish.addAll(dishes);
        return dish;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return name;
    }
}
