package com.pizza.crm.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Класс-контейнер, который хранит в себе список номенклатур. @see {@link Nomenclature}
 * Необходим для того, чтобы в quick menu добавлять непересекающиеся списки ингридиентов
 */
@Entity
@Table(name = "NomenclatureQuickMenu")
public class NomenclatureQuickMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String color;

    private int position;

    @ManyToMany
    @JoinTable(name = "NomenclatureQuick_Nomenclatures",
            joinColumns = @JoinColumn(name = "nomenclatureQuickMenu"),
            inverseJoinColumns = @JoinColumn(name = "nomenclature"))
    private Set<Nomenclature> nomenclatures;

    public NomenclatureQuickMenu(String color, int position, Set<Nomenclature> nomenclatures) {
        this.color = color;
        this.position = position;
        this.nomenclatures = nomenclatures;
    }

    public NomenclatureQuickMenu() {
    }

    public Long getId() {
        return id;
    }

    public Set<Nomenclature> getNomenclatures() {
        return nomenclatures;
    }

    public void setNomenclatures(Set<Nomenclature> nomenclatureSet) {
        this.nomenclatures = nomenclatureSet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "NomenclatureQuickMenu{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", position=" + position +
                ", nomenclatures=" + nomenclatures +
                '}';
    }
}
