package com.pizza.crm.model;

import javax.persistence.*;
import java.util.List;

//TODO связать с номенклатурой
@Entity
@Table(name = "schemeModifiers")
public class SchemeModifiers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String nameScaleOfSize;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "schemeModifiersId")
    private List<ModifierProperty> modifierPropertyList;

    public SchemeModifiers() {
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

    public List<ModifierProperty> getModifierPropertyList() {
        return modifierPropertyList;
    }

    public void setModifierPropertyList(List<ModifierProperty> modifierPropertyList) {
        this.modifierPropertyList = modifierPropertyList;
    }

    public String getNameScaleOfSize() {
        return nameScaleOfSize;
    }

    public void setNameScaleOfSize(String nameScaleofSize) {
        this.nameScaleOfSize = nameScaleofSize;
    }
}
