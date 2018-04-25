package com.pizza.crm.model;

import javax.persistence.*;

@Entity
@Table(name = "modifierProperty")
public class ModifierProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long nomenclatureId;

    private Long modifierId;

    private String name;

    private Integer minimum;

    private Integer byDefault;

    private Integer maximum;

    private Boolean necessarily;

    private Boolean hideIf;

    private Boolean restricted;

    private Integer free;

    public ModifierProperty() {
    }

    public ModifierProperty(Long nomenclatureId, Long modifierId, String name, Integer minimum, Integer byDefault,
                            Integer maximum, Boolean necessarily, Boolean hideIf, Boolean restricted, Integer free) {
        this.nomenclatureId = nomenclatureId;
        this.modifierId = modifierId;
        this.name = name;
        this.minimum = minimum;
        this.byDefault = byDefault;
        this.maximum = maximum;
        this.necessarily = necessarily;
        this.hideIf = hideIf;
        this.restricted = restricted;
        this.free = free;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNomenclatureId() {
        return nomenclatureId;
    }

    public void setNomenclatureId(Long nomenclatureId) {
        this.nomenclatureId = nomenclatureId;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Integer getByDefault() {
        return byDefault;
    }

    public void setByDefault(Integer byDefault) {
        this.byDefault = byDefault;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Boolean getNecessarily() {
        return necessarily;
    }

    public void setNecessarily(Boolean necessarily) {
        this.necessarily = necessarily;
    }

    public Boolean getHideIf() {
        return hideIf;
    }

    public void setHideIf(Boolean hideIf) {
        this.hideIf = hideIf;
    }

    public Boolean getRestricted() {
        return restricted;
    }

    public void setRestricted(Boolean restricted) {
        this.restricted = restricted;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }
}
