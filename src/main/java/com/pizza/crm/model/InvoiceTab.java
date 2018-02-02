package com.pizza.crm.model;


import javax.persistence.*;
import java.util.Set;

@Entity(name="InvoiceTab")
@Table(name = "InvoiceTab")
public class InvoiceTab {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany
    private Set<Invoice> invoice;

    @OneToMany
    private Set<Ingredient> ingredient;

    @Column(name = "count")
    private int count;

    @Column(name = "unit")
    private String unit;

    @Column(name = "costOne")
    private double costOne;

    @Column(name = "allCost")
    private double allCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Invoice> getInvoice() {
        return invoice;
    }

    public void setInvoice(Set<Invoice> invoice) {
        this.invoice = invoice;
    }

    public Set<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Set<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getCostOne() {
        return costOne;
    }

    public void setCostOne(double costOne) {
        this.costOne = costOne;
    }

    public double getAllCost() {
        return allCost;
    }

    public void setAllCost(double allCost) {
        this.allCost = allCost;
    }
}
