package com.pizza.crm.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Order> orders = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Employee_Cooking",
            joinColumns = @JoinColumn(name = "employee"),
            inverseJoinColumns = @JoinColumn(name = "cooking"))
    private Set<Cooking> cooking = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Employee_QualityControl",
            joinColumns = @JoinColumn(name = "employee"),
            inverseJoinColumns = @JoinColumn(name = "qualityControl"))
    private Set<QualityControl> qualityControl = new HashSet<>();

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<DeliveryOrder> deliveryOrder = new ArrayList<>();

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
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

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public Collection<DeliveryOrder> getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(Collection<DeliveryOrder> deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public Set<Cooking> getCooking() {
        return cooking;
    }

    public void setCooking(Set<Cooking> cooking) {
        this.cooking = cooking;
    }

    public Set<QualityControl> getQualityControl() {
        return qualityControl;
    }

    public void setQualityControl(Set<QualityControl> qualityControl) {
        this.qualityControl = qualityControl;
    }
}
