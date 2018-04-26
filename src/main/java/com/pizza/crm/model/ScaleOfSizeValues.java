package com.pizza.crm.model;

import javax.persistence.*;

@Entity
@Table(name = "ScaleOfSizeValues")
public class ScaleOfSizeValues {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int nameSize;

    @Column
    private int kitchenSize;

    @Column
    private boolean defaultSize;

    public ScaleOfSizeValues() {
    }

    public ScaleOfSizeValues(int nameSize, int kitchenSize, boolean defaultSize) {
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

    public int getNameSize() {
        return nameSize;
    }

    public void setNameSize(int nameSize) {
        this.nameSize = nameSize;
    }

    public int getKitchenSize() {
        return kitchenSize;
    }

    public void setKitchenSize(int kitchenSize) {
        this.kitchenSize = kitchenSize;
    }

    public boolean isDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(boolean defaultSize) {
        this.defaultSize = defaultSize;
    }

    @Override
    public String toString() {
        return "ScaleOfSizeValues{" +
                "id=" + id +
                ", nameSize=" + nameSize +
                ", kitchenSize=" + kitchenSize +
                ", defaultSize=" + defaultSize +
                '}';
    }
}
