package com.pizza.crm.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "clientOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salesPoint_id")
    private SalesPoint salesPoint;

    private LocalDateTime dateCreateOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    private DeliveryOrder deliveryOrder;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Cooking> cooking = new ArrayList<>();

    private String paymentMethod;

    private String deliveryAddress;

    private double costNotDiscount;

    private double discount;

    private double costDiscount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<OrderTab> orderTab = new ArrayList<>();

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesPoint getSalesPoint() {
        return salesPoint;
    }

    public void setSalesPoint(SalesPoint salesPoint) {
        this.salesPoint = salesPoint;
    }

    public LocalDateTime getDateCreateOrder() {
        return dateCreateOrder;
    }

    public void setDateCreateOrder(LocalDateTime dateCreateOrder) {
        this.dateCreateOrder = dateCreateOrder;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

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

    public Collection<OrderTab> getOrderTab() {
        return orderTab;
    }

    public void setOrderTab(Collection<OrderTab> orderTab) {
        this.orderTab = orderTab;
    }

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public Collection<Cooking> getCooking() {
        return cooking;
    }

    public void setCooking(Collection<Cooking> cooking) {
        this.cooking = cooking;
    }
}
