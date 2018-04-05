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
    private String description;

    @Column
    private String basic;

    @Column
    private int code;


    public UnitsOfMeasurement() {
    }

    public UnitsOfMeasurement(String name, String description, String basic, int code) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
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
                ", description='" + description + '\'' +
                ", basic='" + basic + '\'' +
                ", code=" + code +
                '}';
    }
}
