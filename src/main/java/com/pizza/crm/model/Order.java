package com.pizza.crm.model;

import javax.persistence.*;
import java.sql.Date;


@Entity(name="Order")
@Table(name = "Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

//    private SalesPoint salesPoint;

    @Column(name = "dateCreateOrder")
    private Date dateCreateOrder;

//    private Employee employee;

//    private Client client;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @Column(name = "deliveryAddress")
    private String deliveryAddress;

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

//    public SalesPoint getSalesPoint() {
//        return salesPoint;
//    }
//
//    public void setSalesPoint(SalesPoint salesPoint) {
//        this.salesPoint = salesPoint;
//    }

    public Date getDateCreateOrder() {
        return dateCreateOrder;
    }

    public void setDateCreateOrder(Date dateCreateOrder) {
        this.dateCreateOrder = dateCreateOrder;
    }

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//
//    public Client getClient() {
//        return client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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
