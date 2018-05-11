package com.pizza.crm.model;

import javax.persistence.*;

@Entity
@Table(name = "scale_values")
public class ScaleOfSizeValues {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nameSize;

    @Column
    private String kitchenSize;

    @Column
    private boolean defaultSize;

    @Column
    private boolean removed;

    public ScaleOfSizeValues() {
    }

    public ScaleOfSizeValues(Long id, String nameSize, String kitchenSize, boolean defaultSize) {
        this.id = id;
        this.nameSize = nameSize;
        this.kitchenSize = kitchenSize;
        this.defaultSize = defaultSize;
    }

    public ScaleOfSizeValues(String nameSize, String kitchenSize, boolean defaultSize) {
        this.nameSize = nameSize;
        this.kitchenSize = kitchenSize;
        this.defaultSize = defaultSize;
    }

    public ScaleOfSizeValues(String nameSize, String kitchenSize, boolean defaultSize, boolean removed) {
        this.nameSize = nameSize;
        this.kitchenSize = kitchenSize;
        this.defaultSize = defaultSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSize() {
        return nameSize;
    }

    public void setNameSize(String nameSize) {
        this.nameSize = nameSize;
    }

    public String getKitchenSize() {
        return kitchenSize;
    }

    public void setKitchenSize(String kitchenSize) {
        this.kitchenSize = kitchenSize;
    }

    public boolean isDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(boolean defaultSize) {
        this.defaultSize = defaultSize;
    }

    public boolean getRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
    @Override
    public String toString() {
        return "ScaleOfSizeValuesRepository{" +
                "id=" + id +
                ", nameSize=" + nameSize +
                ", kitchenSize=" + kitchenSize +
                ", defaultSize=" + defaultSize +
                '}';
    }
}
