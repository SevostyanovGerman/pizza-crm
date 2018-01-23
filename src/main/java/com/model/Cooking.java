package com.model;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.Set;

@Entity(name="Cooking")
@Table(name = "Cooking")
public class Cooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private Employee employee;

    @OneToMany
    private Set<Order> order;

    private Set<OpderTab> opderTab;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startTime")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endTime")
    private Date endTime;

    @Column(name = "typeWork")
    private TypeWork typeWork;

    @Column(name = "salesPoint")
    private SalesPoint salesPoint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }

    public Set<OpderTab> getOpderTab() {
        return opderTab;
    }

    public void setOpderTab(Set<OpderTab> opderTab) {
        this.opderTab = opderTab;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public TypeWork getTypeWork() {
        return typeWork;
    }

    public void setTypeWork(TypeWork typeWork) {
        this.typeWork = typeWork;
    }

    public SalesPoint getSalesPoint() {
        return salesPoint;
    }

    public void setSalesPoint(SalesPoint salesPoint) {
        this.salesPoint = salesPoint;
    }
}
