package com.pizza.crm.model;

import javax.persistence.*;


@Entity(name="OpderTab")
@Table(name = "OpderTab")
public class OpderTab {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "order")
    private Order order;

    @Column(name = "lineNumber")
    private int lineNumber;

    @Column(name = "dish")
    private Dish dish;

    @Column(name = "count")
    private int count;

    @Column(name = "costOne")
    private double costOne;

    @Column(name = "costNotDiscount")
    private double costNotDiscount;

    @Column(name = "discount")
    private double discount;

    @Column(name = "costDiscount")
    private double costDiscount;

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
}
