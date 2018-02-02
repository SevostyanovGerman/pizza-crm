package com.pizza.crm.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ResidueRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "residueRegister")
    private Set<Ingredient> ingredient = new HashSet<>();

    @ManyToMany(mappedBy = "residueRegister")
    private Set<Stock> stock = new HashSet<>();

    private double period;

    private int count;

    private String registrar;

    public ResidueRegister() {
    }

    public ResidueRegister(Set<Ingredient> ingredient, Set<Stock> stock, double period, int count, String registrar) {
        this.ingredient = ingredient;
        this.stock = stock;
        this.period = period;
        this.count = count;
        this.registrar = registrar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Set<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public Set<Stock> getStock() {
        return stock;
    }

    public void setStock(Set<Stock> stock) {
        this.stock = stock;
    }

    public double getPeriod() {
        return period;
    }

    public void setPeriod(double period) {
        this.period = period;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }
}
