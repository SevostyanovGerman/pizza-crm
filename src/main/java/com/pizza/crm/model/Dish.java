package com.pizza.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "name", unique = true)
    private String name;

    private double price;

    private String code;

    private String barcode;

    private String vendorCode;

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

    @ManyToMany
    @JoinTable(name = "Dish_Dicree",
            joinColumns = @JoinColumn(name = "dish"),
            inverseJoinColumns = @JoinColumn(name = "decree"))
    private Set<Decree> decrees;

    @ManyToMany
    @JoinTable(name = "DishQuickMenu_Dish",
            joinColumns = @JoinColumn(name = "dish"),
            inverseJoinColumns = @JoinColumn(name = "dishQuickMenu"))
    @JsonBackReference
    private Set<DishQuickMenu> dishQuickMenu;


    public Dish() {
    }

    public Dish(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dish(@NotBlank String name, double price, String code, String barcode, String vendorCode, Set<Ingredient> ingredients, Set<Category> categories, Set<DishQuickMenu> dishQuickMenu) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.barcode = barcode;
        this.vendorCode = vendorCode;
        this.ingredients = ingredients;
        this.categories = categories;
        this.dishQuickMenu = dishQuickMenu;
    }

    public Dish(@NotBlank String name, double price, String code, String barcode, String vendorCode, Set<Ingredient> ingredients, Set<Category> categories, Set<Decree> decrees, Set<DishQuickMenu> dishQuickMenu) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.barcode = barcode;
        this.vendorCode = vendorCode;
        this.ingredients = ingredients;
        this.categories = categories;
        this.decrees = decrees;
        this.dishQuickMenu = dishQuickMenu;
    }

    public Set<DishQuickMenu> getDishQuickMenu() {
        return dishQuickMenu;
    }

    public void setDishQuickMenu(Set<DishQuickMenu> dishQuickMenu) {
        this.dishQuickMenu = dishQuickMenu;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Dish(@NotBlank String name, double price, String code, String barcode, String vendorCode) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.barcode = barcode;
        this.vendorCode = vendorCode;
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

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.getDishes().add(this);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
        ingredient.getDishes().remove(this);
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", code='" + code + '\'' +
                ", barcode='" + barcode + '\'' +
                ", vendorCode='" + vendorCode + '\'' +
                ", ingredients=" + ingredients +
                ", categories=" + categories +
                ", dishQuickMenu=" + dishQuickMenu +
                '}';
    }
}
