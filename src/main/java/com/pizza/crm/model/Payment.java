package com.pizza.crm.model;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "paymentid")
    private long id;

    @Column(name = "cash")
    private double cash;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private PaymentType paymentType;

    public Payment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
