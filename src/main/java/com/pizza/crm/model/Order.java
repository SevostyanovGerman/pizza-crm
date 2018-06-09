package com.pizza.crm.model;

import com.pizza.crm.model.discount.Discount;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "ClientOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salesPoint")
    private SalesPoint salesPoint;

    private LocalDateTime dateCreateOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client")
    private Client client;

    @OneToOne
    private DeliveryOrder deliveryOrder;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Cooking> cooking = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "ClientOrder_PaymentMethod",
            joinColumns = @JoinColumn(name = "ClientOrder"),
            inverseJoinColumns = @JoinColumn(name = "PaymentMethod"))
    private List<PaymentMethod> paymentMethods;

    @ManyToMany
    @JoinTable(name = "ClientOrder_Discount",
            joinColumns = @JoinColumn(name = "ClientOrder"),
            inverseJoinColumns = @JoinColumn(name = "Discount"))
    private List<Discount> discounts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ClientOrder_Dish",
            joinColumns = @JoinColumn(name = "ClientOrder"),
            inverseJoinColumns = @JoinColumn(name = "Dish"))
    private List<Dish> dishes;

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

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public List<Dish> getDishes() {
        return dishes;
    }


    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
