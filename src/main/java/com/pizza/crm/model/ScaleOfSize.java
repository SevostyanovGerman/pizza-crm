package com.pizza.crm.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ScaleOfSizeService")
public class ScaleOfSize {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScaleOfSizeValues> valuesList = new ArrayList<>();

    public ScaleOfSize() {
    }

    public ScaleOfSize(String name, List<ScaleOfSizeValues> valuesList) {
        this.name = name;
        this.valuesList = valuesList;
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

    public List<ScaleOfSizeValues> getValuesList() {
        return valuesList;
    }

    public void setValuesList(List<ScaleOfSizeValues> valuesList) {
        this.valuesList = valuesList;
    }

    @Override
    public String toString() {
        return "ScaleOfSizeService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", valuesList=" + valuesList +
                '}';
    }
}
