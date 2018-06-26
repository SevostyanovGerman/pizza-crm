package com.pizza.crm.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "scale")
public class ScaleOfSize {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nameScale;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScaleOfSizeValues> valuesList = new ArrayList<>();

    public ScaleOfSize() {
    }

    public ScaleOfSize(String nameScale) {
        this.nameScale = nameScale;
    }


    public ScaleOfSize(Long id, String nameScale, List<ScaleOfSizeValues> valuesList) {
        this.id = id;
        this.nameScale = nameScale;
        this.valuesList = valuesList;
    }

    public ScaleOfSize(String nameScale, List<ScaleOfSizeValues> valuesList) {
        this.nameScale = nameScale;
        this.valuesList = valuesList;
    }

    public String getNameScale() {
        return nameScale;
    }

    public void setNameScale(String nameScale) {
        this.nameScale = nameScale;
    }

    public List<ScaleOfSizeValues> getValuesList() {
        return valuesList;
    }

    public void setValuesList(List<ScaleOfSizeValues> valuesList) {
        this.valuesList = valuesList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return nameScale;
    }

    public void setName(String name) {
        this.nameScale = name;
    }

    @Override
    public String toString() {
        return "ScaleOfSize{" +
                "id=" + id +
                ", nameScale='" + nameScale + '\'' +
                ", valuesList=" + valuesList +
                '}';
    }
}
