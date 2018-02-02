package com.pizza.crm.model;

import javax.persistence.*;
import java.sql.Date;

@Entity(name="QualityСontrol")
@Table(name = "QualityСontrol")
public class QualityСontrol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    private Employee employee;

    @OneToOne
    private Cooking cooking;

    @Column(name = "verdict")
    private byte verdict;

    @Column(name = "comment")
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createTime")
    private Date createTime;

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

    public Cooking getCooking() {
        return cooking;
    }

    public void setCooking(Cooking cooking) {
        this.cooking = cooking;
    }

    public byte getVerdict() {
        return verdict;
    }

    public void setVerdict(byte verdict) {
        this.verdict = verdict;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SalesPoint getSalesPoint() {
        return salesPoint;
    }

    public void setSalesPoint(SalesPoint salesPoint) {
        this.salesPoint = salesPoint;
    }
}
