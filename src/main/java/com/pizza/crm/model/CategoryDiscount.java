package com.pizza.crm.model;

import javax.persistence.*;

@Entity
@Table(name = "CategoryDiscount")
public class CategoryDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String categoryName;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private Integer value;

    public CategoryDiscount() {
        categoryName = "None";
        discountType = DiscountType.NONE;
        value = 0;
    }

    public CategoryDiscount(String categoryName, DiscountType discountType, Integer value) {
        this.categoryName = categoryName;
        this.discountType = discountType;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
