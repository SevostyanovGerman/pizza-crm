package com.pizza.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nomenclatureParentGroup")
public class NomenclatureParentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "Nomenclature_NomenclatureParentGroup",
            joinColumns = @JoinColumn(name = "NomenclatureParentGroup"),
            inverseJoinColumns = @JoinColumn(name = "nomenclature"))
    @JsonBackReference
    private List<Nomenclature> nomenclatures;

    public NomenclatureParentGroup() {
    }

    public NomenclatureParentGroup(String name) {
        this.name = name;
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

    public List<Nomenclature> getNomenclatures() {
        return nomenclatures;
    }

    public void setNomenclatures(List<Nomenclature> nomenclatures) {
        this.nomenclatures = nomenclatures;
    }
}
