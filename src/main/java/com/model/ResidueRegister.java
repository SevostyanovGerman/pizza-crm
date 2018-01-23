package com.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name="ResidueRegister")
@Table(name = "ResidueRegister")
public class ResidueRegister {

    @OneToMany
    private Set<Product> product;

    private Set<Stock> stock;

    @Column(name = "period")
    private double period;

    @Column(name = "count")
    private int count;

    @Column(name = "registrar")
    private String registrar;

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
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
