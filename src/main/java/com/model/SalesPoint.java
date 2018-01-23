package com.model;

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
    private boolean shoppingHall;

    @Column(name = "Delivery")
    private boolean Delivery;

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

    public boolean isShoppingHall() {
        return shoppingHall;
    }

    public void setShoppingHall(boolean shoppingHall) {
        this.shoppingHall = shoppingHall;
    }

    public boolean isDelivery() {
        return Delivery;
    }

    public void setDelivery(boolean delivery) {
        Delivery = delivery;
    }
}
