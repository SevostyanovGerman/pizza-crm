package com.pizza.crm.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OrderTab {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "orderTab_cooking",
            joinColumns = @JoinColumn(name = "orderTab_id"),
            inverseJoinColumns = @JoinColumn(name = "cooking_id"))
    private Set<Cooking> cooking = new HashSet<>();

    private int lineNumber;

    @OneToOne
    private Dish dish;

    private int count;

    private double costOne;

    private double costNotDiscount;

    private double discount;

    private double costDiscount;

    public OrderTab() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getCostOne() {
        return costOne;
    }

    public void setCostOne(double costOne) {
        this.costOne = costOne;
    }

    public double getCostNotDiscount() {
        return costNotDiscount;
    }

    public void setCostNotDiscount(double costNotDiscount) {
        this.costNotDiscount = costNotDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCostDiscount() {
        return costDiscount;
    }

    public void setCostDiscount(double costDiscount) {
        this.costDiscount = costDiscount;
    }

    public Set<Cooking> getCooking() {
        return cooking;
    }

    public void setCooking(Set<Cooking> cooking) {
        this.cooking = cooking;
    }
}
