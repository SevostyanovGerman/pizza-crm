package com.pizza.crm.model;


import javax.persistence.*;
import java.sql.Date;

@Entity(name="DeliveryOrder")
@Table(name = "DeliveryOrder")
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

//    @Column(name = "courier")
//    private Employee courier;

    @Column(name = "checkOutTime")
    private Date checkOutTime;

    @Column(name = "timeReturn")
    private Date timeReturn;

//    private SalesPoint salesPoint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Employee getCourier() {
//        return courier;
//    }
//
//    public void setCourier(Employee courier) {
//        this.courier = courier;
//    }

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

//    public SalesPoint getSalesPoint() {
//        return salesPoint;
//    }
//
//    public void setSalesPoint(SalesPoint salesPoint) {
//        this.salesPoint = salesPoint;
//    }
}
