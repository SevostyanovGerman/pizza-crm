package com.pizza.crm.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "validity")
public class Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String nameValidity;

    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ValiditySchedule> validityScheduleList = new ArrayList<>();

    public Validity(){
    }

    public Validity(String nameValidity) {
        this.nameValidity = nameValidity;
    }

    public Validity(String nameValidity, List<ValiditySchedule> validityScheduleList) {
        this.nameValidity = nameValidity;
        this.validityScheduleList = validityScheduleList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameValidity() {
        return nameValidity;
    }

    public void setNameValidity(String nameValidity) {
        this.nameValidity = nameValidity;
    }

    public List<ValiditySchedule> getValidityScheduleList() {
        return validityScheduleList;
    }

    public void setValidityScheduleList(List<ValiditySchedule> validityScheduleList) {
        this.validityScheduleList = validityScheduleList;
    }
}
