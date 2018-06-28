package com.pizza.crm.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.DayOfWeek;
import java.util.Set;

/**
 * Кнопка выбора списков позиций для выбора быстрого меню, хранит в себе список контейнеров @see {@link NomenclatureQuickMenu}
 */
@Entity
@Table(name = "QuickMenu")
public class QuickMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany
    @JoinTable(name = "Nomenclature_Quick_menus",
            joinColumns = @JoinColumn(name = "quickmenu"),
            inverseJoinColumns = @JoinColumn(name = "nomenclatureQuickMenu"))
    private Set<NomenclatureQuickMenu> nomenclatureQuickMenus;

    private DayOfWeek dayOfWeek;

    public QuickMenu(@NotBlank String name, Set<NomenclatureQuickMenu> nomenclatureQuickMenus, DayOfWeek dayOfWeek) {
        this.name = name;
        this.nomenclatureQuickMenus = nomenclatureQuickMenus;
        this.dayOfWeek = dayOfWeek;
    }

    public QuickMenu() {
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

    public Set<NomenclatureQuickMenu> getNomenclatureQuickMenus() {
        return nomenclatureQuickMenus;
    }

    public void setNomenclatureQuickMenus(Set<NomenclatureQuickMenu> nomenclatureQuickMenus) {
        this.nomenclatureQuickMenus = nomenclatureQuickMenus;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public String toString() {
        return "QuickMenu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nomenclatureQuickMenus=" + nomenclatureQuickMenus +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
