package com.pizza.crm.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private double price;

    private String type;

    private String unit;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Dish> dishes = new HashSet<>();

    @ManyToMany(mappedBy = "ingredients")
    private Set<InvoiceTab> invoiceTabs = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "ingredient_residueRegister",
        joinColumns = @JoinColumn(name = "ingredient_id"),
        inverseJoinColumns = @JoinColumn(name = "residueRegister_id"))
    private Set<ResidueRegister> residueRegister = new HashSet<>();

    public Ingredient() {
    }

    public Ingredient(String name, double price, String type, String unit) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.unit = unit;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Set<InvoiceTab> getInvoiceTabs() {
        return invoiceTabs;
    }

    public void setInvoiceTabs(Set<InvoiceTab> invoiceTabs) {
        this.invoiceTabs = invoiceTabs;
    }

    public Set<ResidueRegister> getResidueRegister() {
        return residueRegister;
    }

    public void setResidueRegister(Set<ResidueRegister> residueRegister) {
        this.residueRegister = residueRegister;
    }
}
