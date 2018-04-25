package com.pizza.crm.model;

import javax.persistence.*;

@Entity
@Table(name = "packaging")
public class Packaging {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer code;

    private Integer quantityInPackaging;

    private Boolean backIn;

    private String unitOfMeasurement;

    public Packaging() {
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getQuantityInPackaging() {
        return quantityInPackaging;
    }

    public void setQuantityInPackaging(Integer quantityInPackaging) {
        this.quantityInPackaging = quantityInPackaging;
    }

    public Boolean getBackIn() {
        return backIn;
    }

    public void setBackIn(Boolean backIn) {
        this.backIn = backIn;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
}
