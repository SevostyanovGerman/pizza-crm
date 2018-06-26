package com.pizza.crm.model.discount;

import com.pizza.crm.model.Category;

import javax.persistence.*;

@Entity
@Table(name = "DiscountCategory")
public class DiscountCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private DiscountMode discountMode;

    private int value;

    public DiscountCategory(Discount discount, String name, DiscountMode discountMode, int value) {
        this.name = name;
        this.discountMode = discountMode;
        this.value = value;
    }

    public DiscountCategory() {
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

    public DiscountMode getDiscountMode() {
        return discountMode;
    }

    public void setDiscountMode(DiscountMode discountMode) {
        this.discountMode = discountMode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
