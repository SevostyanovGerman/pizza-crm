package com.pizza.crm.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String checkName;

    private String type;

    private Boolean acceptManualDiscount;

    private Integer minSum;

    private Boolean manualInput;

    private Boolean setAuto;

    private Boolean stewardChoice;

    private Boolean discountWithOther;

    private Boolean active;

    private Boolean applyForAllType;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @Enumerated(EnumType.STRING)
    private DiscountValueType discountValueType;

    private Integer value;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "DiscountAndPayment_id")
    private DiscountAndPayment discountAndPayment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "discount_id")
    private List<ActionTime> actionTimeList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "discount_id")
    @Fetch(FetchMode.SELECT)
    private List<CategoryDiscount> categoryDiscounts;

    public Discount() {
        minSum = 0;
        manualInput = false;
        setAuto = false;
        stewardChoice = false;
        discountWithOther = false;
        active = false;
        discountAndPayment = new DiscountAndPayment();
        categoryDiscounts = new ArrayList<>();
    }

    public Discount(String name, String checkName, String type, Boolean acceptManualDiscount,
                    Integer minSum, List<ActionTime> actionTimeList, DiscountAndPayment discountAndPayment) {
        this.name = name;
        this.checkName = checkName;
        this.type = type;
        this.acceptManualDiscount = acceptManualDiscount;
        this.minSum = minSum;
        this.actionTimeList = actionTimeList;
        this.discountAndPayment = discountAndPayment;
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

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getAcceptManualDiscount() {
        return acceptManualDiscount;
    }

    public void setAcceptManualDiscount(Boolean acceptManualDiscount) {
        this.acceptManualDiscount = acceptManualDiscount;
    }

    public Integer getMinSum() {
        return minSum;
    }

    public void setMinSum(Integer minSum) {
        this.minSum = minSum;
    }

    public List<ActionTime> getActionTimeList() {
        return actionTimeList;
    }

    public void setActionTimeList(List<ActionTime> actionTimeList) {
        this.actionTimeList = actionTimeList;
    }

    public Boolean getManualInput() {
        return manualInput;
    }

    public void setManualInput(Boolean manualInput) {
        this.manualInput = manualInput;
    }

    public Boolean getSetAuto() {
        return setAuto;
    }

    public void setSetAuto(Boolean setAuto) {
        this.setAuto = setAuto;
    }

    public Boolean getStewardChoice() {
        return stewardChoice;
    }

    public void setStewardChoice(Boolean stewardChoice) {
        this.stewardChoice = stewardChoice;
    }

    public Boolean getDiscountWithOther() {
        return discountWithOther;
    }

    public void setDiscountWithOther(Boolean discountWithOther) {
        this.discountWithOther = discountWithOther;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public DiscountAndPayment getDiscountAndPayment() {
        return discountAndPayment;
    }

    public void setDiscountAndPayment(DiscountAndPayment discountAndPayment) {
        this.discountAndPayment = discountAndPayment;
    }

    public List<CategoryDiscount> getCategoryDiscounts() {
        return categoryDiscounts;
    }

    public void setCategoryDiscounts(List<CategoryDiscount> categoryDiscounts) {
        this.categoryDiscounts = categoryDiscounts;
    }

    public Boolean getApplyForAllType() {
        return applyForAllType;
    }

    public void setApplyForAllType(Boolean applyForAllType) {
        this.applyForAllType = applyForAllType;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public DiscountValueType getDiscountValueType() {
        return discountValueType;
    }

    public void setDiscountValueType(DiscountValueType discountValueType) {
        this.discountValueType = discountValueType;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
