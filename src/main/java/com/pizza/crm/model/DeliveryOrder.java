package com.pizza.crm.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "DeliveryOrder")
public class DeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courier")
    private Employee courier;

    private LocalDateTime checkOutTime;

    private LocalDateTime timeReturn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salesPoint")
    private SalesPoint salesPoint;

    @OneToMany(mappedBy = "deliveryOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<DeliveryOrderTab> deliveryOrderTab = new ArrayList<>();

    @OneToOne(mappedBy = "deliveryOrder")
    private Order order;

    public DeliveryOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getCourier() {
        return courier;
    }

    public void setCourier(Employee courier) {
        this.courier = courier;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public LocalDateTime getTimeReturn() {
        return timeReturn;
    }

    public void setTimeReturn(LocalDateTime timeReturn) {
        this.timeReturn = timeReturn;
    }

    public SalesPoint getSalesPoint() {
        return salesPoint;
    }

    public void setSalesPoint(SalesPoint salesPoint) {
        this.salesPoint = salesPoint;
    }

    public Collection<DeliveryOrderTab> getDeliveryOrderTab() {
        return deliveryOrderTab;
    }

    public void setDeliveryOrderTab(Collection<DeliveryOrderTab> deliveryOrderTab) {
        this.deliveryOrderTab = deliveryOrderTab;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
