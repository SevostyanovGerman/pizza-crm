package com.pizza.crm.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1)
    @Column(name = "name", unique = true)
    private String name;

    private double price;

    @ManyToMany
    @JoinTable(name = "Dish_Ingredient",
            joinColumns = @JoinColumn(name = "dish"),
            inverseJoinColumns = @JoinColumn(name = "ingredient"))
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "Dish_Category",
            joinColumns = @JoinColumn(name = "dish"),
            inverseJoinColumns = @JoinColumn(name = "category"))
    private Set<Category> categories;

    public Dish() {
    }

    public Dish(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dish(String name) {
        this.name = name;
    }

    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Ingredient> getIngredient() {
        return ingredients;
    }

    public void setIngredient(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Category> getCategories() {
        return categories;
    }

//    public void addCategory(Category newCategory) {
//        categories.add(newCategory);
//        //newCategory.getDish().add(this);
//    }
//
//    public void removeCategory(Category removedCategory) {
//        categories.remove(removedCategory);
//        //removedCategory.getDish().remove(this);
//    }

    @Override
    public String toString() {
        return name;
    }

}
