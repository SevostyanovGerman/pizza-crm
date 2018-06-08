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

    private LocalDateTime creationDate;

    private LocalDateTime closingDate;

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

    @ManyToMany
    @JoinTable(name = "ClientOrder_Dish",
            joinColumns = @JoinColumn(name = "ClientOrder"),
            inverseJoinColumns = @JoinColumn(name = "Dish"))
    private List<Dish> dishes;

    private String deliveryAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<OrderTab> orderTab = new ArrayList<>();

    private Double price;

    private Double discountedPrice;

    private Double discountCost;

    private Double extraChargeCost;

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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
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

    public LocalDateTime getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDateTime closingDate) {
        this.closingDate = closingDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Double getDiscountCost() {
        return discountCost;
    }

    public void setDiscountCost(Double discountCost) {
        this.discountCost = discountCost;
    }

    public Double getExtraChargeCost() {
        return extraChargeCost;
    }

    public void setExtraChargeCost(Double extraChargeCost) {
        this.extraChargeCost = extraChargeCost;
    }
}
