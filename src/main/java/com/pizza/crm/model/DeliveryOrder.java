package com.pizza.crm.model;


import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class DeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courier_id")
    private Employee courier;

    private Date checkOutTime;

    private Date timeReturn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salesPoint_id")
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

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public Date getTimeReturn() {
        return timeReturn;
    }

    public void setTimeReturn(Date timeReturn) {
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
