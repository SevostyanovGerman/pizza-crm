package com.pizza.crm.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class InvoiceTab {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "invoiceTab_ingredient",
            joinColumns = @JoinColumn(name = "invoiceTab_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredient = new HashSet<>();

    private int count;

    private String unit;

    private double costOne;

    private double allCost;

    public InvoiceTab() {
    }

    public InvoiceTab(Invoice invoice, int count, String unit, double costOne, double allCost) {
        this.invoice = invoice;
        this.count = count;
        this.unit = unit;
        this.costOne = costOne;
        this.allCost = allCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Set<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Set<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }
}
