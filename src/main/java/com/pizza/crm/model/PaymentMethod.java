package com.pizza.crm.model;

import com.pizza.crm.model.discount.Discount;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PaymentMethod")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paymentType")
    @JsonManagedReference
    private PaymentType paymentType;

    private boolean printCashBill;
    private String nameInCashBill;
    private boolean combinable;
    private boolean manualInput;
    private String comment;
    private boolean enabled = true;


    public PaymentMethod(String name, PaymentType paymentType, Discount discount, Boolean printCashBill, String nameInCashBill,
                         Boolean combinable, Boolean manualInput, String comment, Boolean enabled) {
        this.name = name;
        this.paymentType = paymentType;
        this.discount = discount;
        this.printCashBill = printCashBill;
        this.nameInCashBill = nameInCashBill;
        this.combinable = combinable;
        this.manualInput = manualInput;
        this.comment = comment;
        this.enabled = enabled;
    }

    public PaymentMethod(String name, PaymentType paymentType) {
        this.name = name;
        this.paymentType = paymentType;
        this.nameInCashBill = name;
    }

    public PaymentMethod() {
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isPrintCashBill() {
        return printCashBill;
    }

    public void setPrintCashBill(boolean printCashBill) {
        this.printCashBill = printCashBill;
    }

    public String getNameInCashBill() {
        return nameInCashBill;
    }

    public void setNameInCashBill(String nameInCashBill) {
        this.nameInCashBill = nameInCashBill;
    }

    public boolean isCombinable() {
        return combinable;
    }

    public void setCombinable(boolean combinable) {
        this.combinable = combinable;
    }

    public boolean isManualInput() {
        return manualInput;
    }

    public void setManualInput(boolean manualInput) {
        this.manualInput = manualInput;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
}
