package com.pizza.crm.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    private double price;

    private String type;

    private String unit;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Dish> dishes = new HashSet<>();

    @ManyToMany(mappedBy = "ingredient")
    private Set<InvoiceTab> invoiceTab = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Ingredient_ResidueRegister",
            joinColumns = @JoinColumn(name = "ingredient"),
            inverseJoinColumns = @JoinColumn(name = "residueRegister"))
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

    public Set<InvoiceTab> getInvoiceTab() {
        return invoiceTab;
    }

    public void setInvoiceTab(Set<InvoiceTab> invoiceTab) {
        this.invoiceTab = invoiceTab;
    }

    public Set<ResidueRegister> getResidueRegister() {
        return residueRegister;
    }

    public void setResidueRegister(Set<ResidueRegister> residueRegister) {
        this.residueRegister = residueRegister;
    }

    @Override
    public String toString() {
        return name;
    }
}
