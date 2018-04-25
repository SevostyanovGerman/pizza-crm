package com.pizza.crm.model;

import javax.persistence.*;

@Entity
@Table(name = "UnitsOfMeasurement")
public class UnitsOfMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String shortName;

    @Column
    private Boolean basic;

    @Column
    private Integer code;


    public UnitsOfMeasurement() {
    }

    public UnitsOfMeasurement(String name, String shortName, Boolean basic, Integer code) {
        this.name = name;
        this.shortName = shortName;
        this.basic = basic;
        this.code = code;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Boolean getBasic() {
        return basic;
    }

    public void setBasic(Boolean basic) {
        this.basic = basic;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UnitsOfMeasurement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", basic='" + basic + '\'' +
                ", code=" + code +
                '}';
    }
}
