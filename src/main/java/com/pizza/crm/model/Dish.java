package com.pizza.crm.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "dish_ingredient",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "dish_dishCategory",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "dishCategory_id"))
    private Set<Category> categories = new HashSet<>();

    public Dish() {
    }

    public Dish(String name) {
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

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            return;
        }
        ingredients.add(ingredient);
        ingredient.addDush(this);
    }

    public void removeIngredient(Ingredient ingredient) {
        if (!ingredients.contains(ingredient)) {
            return;
        }
        ingredients.remove(ingredient);
        ingredient.removeDish(this);
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        if (categories.contains(category)) {
            return;
        }
        categories.add(category);
        category.addDish(this);
    }

    public void removeCategory(Category category) {
        if (!categories.contains(category)) {
            return;
        }
        categories.remove(category);
        category.removeDish(this);
    }

}
