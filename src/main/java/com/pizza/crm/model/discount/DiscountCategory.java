package com.pizza.crm.model.discount;

import com.pizza.crm.model.Category;

import javax.persistence.*;

@Entity
@Table(name = "DiscountCategory")
public class DiscountCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "discount")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @Enumerated(EnumType.STRING)
    private DiscountMode discountMode;

    private int value;

    public DiscountCategory(Discount discount, Category category, DiscountMode discountMode, int value) {
        this.discount = discount;
        this.category = category;
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

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
