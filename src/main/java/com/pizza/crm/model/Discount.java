package com.pizza.crm.model;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "discount_id")
    private List<ActionTime> actionTimeList;

    public Discount() {
        minSum = 0;
        manualInput = false;
        setAuto = false;
        stewardChoice =false;
        discountWithOther = false;
        active = false;
    }

    public Discount(String name, String checkName, String type, Boolean acceptManualDiscount,
                    Integer minSum, List<ActionTime> actionTimeList) {
        this.name = name;
        this.checkName = checkName;
        this.type = type;
        this.acceptManualDiscount = acceptManualDiscount;
        this.minSum = minSum;
        this.actionTimeList = actionTimeList;
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
}
