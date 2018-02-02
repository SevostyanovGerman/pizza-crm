package com.pizza.crm.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name="ResidueRegister")
@Table(name = "ResidueRegister")
public class ResidueRegister {

    @OneToMany
    private Set<Ingredient> ingredient;

    private Set<Stock> stock;

    @Column(name = "period")
    private double period;

    @Column(name = "count")
    private int count;

    @Column(name = "registrar")
    private String registrar;

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
