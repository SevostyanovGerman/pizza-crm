package com.pizza.crm.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class QualityControl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "qualityControls")
    private Set<Employee> employees = new HashSet<>();

    @OneToOne(mappedBy = "qualityControl")
    private Cooking cooking;

    private byte verdict;

    private String comment;

    private Date createTime;

//    @Column(name = "salesPoint")
//    private SalesPoint salesPoint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Cooking getCooking() {
//        return cooking;
//    }
//
//    public void setCooking(Cooking cooking) {
//        this.cooking = cooking;
//    }

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

    public Cooking getCooking() {
        return cooking;
    }

    public void setCooking(Cooking cooking) {
        this.cooking = cooking;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}
