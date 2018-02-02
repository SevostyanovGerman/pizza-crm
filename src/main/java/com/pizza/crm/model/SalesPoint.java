package com.pizza.crm.model;

import javax.persistence.*;

@Entity(name="SalesPoint")
@Table(name = "SalesPoint")
public class SalesPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "shoppingHall")
    private byte shoppingHall;

    @Column(name = "Delivery")
    private byte Delivery;

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

    public byte getShoppingHall() {
        return shoppingHall;
    }

    public void setShoppingHall(byte shoppingHall) {
        this.shoppingHall = shoppingHall;
    }

    public byte isDelivery() {
        return Delivery;
    }

    public void setDelivery(byte delivery) {
        Delivery = delivery;
    }
}
