package com.pizza.crm.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Dish> dishes = new HashSet<>();

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
        return dishes;
    }

    public void addDish(Dish newDish) {
        if (dishes.contains(newDish)) {
            return;
        }
        dishes.add(newDish);
        newDish.addCategory(this);
    }

    public void removeDish(Dish removedDish) {
        if (!dishes.contains(removedDish)) {
            return;
        }
        dishes.remove(removedDish);
        removedDish.removeCategory(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
