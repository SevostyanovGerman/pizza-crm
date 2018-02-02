package com.pizza.crm.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name="Stock")
@Table(name = "Stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

//    @OneToMany
//    private Set<SalesPoint> salesPoint;

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

//    public Set<SalesPoint> getSalesPoint() {
//        return salesPoint;
//    }
//
//    public void setSalesPoint(Set<SalesPoint> salesPoint) {
//        this.salesPoint = salesPoint;
//    }
}
