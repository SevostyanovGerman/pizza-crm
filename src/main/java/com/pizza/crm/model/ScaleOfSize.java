package com.pizza.crm.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "scale")
public class ScaleOfSize {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nameScale;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection <ScaleOfSizeValues> valuesList = new ArrayList<>();

    public ScaleOfSize() {
    }


    public ScaleOfSize(String nameScale, Collection<ScaleOfSizeValues> valuesList) {
        this.nameScale = nameScale;
        this.valuesList = valuesList;
    }

    public String getNameScale() {
        return nameScale;
    }

    public void setNameScale(String nameScale) {
        this.nameScale = nameScale;
    }

    public Collection<ScaleOfSizeValues> getValuesList() {
        return valuesList;
    }

    public void setValuesList(Collection<ScaleOfSizeValues> valuesList) {
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
