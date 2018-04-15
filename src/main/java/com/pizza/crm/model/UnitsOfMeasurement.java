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
    private boolean basic;

    @Column
    private int code;


    public UnitsOfMeasurement() {
    }

    public UnitsOfMeasurement(Long id, String name, String shortName, boolean basic, int code) {
        this.id = id;
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

    public boolean getBasic() {
        return basic;
    }

    public void setBasic(boolean basic) {
        this.basic = basic;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
