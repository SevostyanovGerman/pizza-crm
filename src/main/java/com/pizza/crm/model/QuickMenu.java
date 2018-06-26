package com.pizza.crm.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Table(name = "QuickMenu")
public class QuickMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    //TODO херня какая-то сделать список номенклатур
    @ManyToMany
    @JoinTable(name = "Dish_QuickMenu_more",
            joinColumns = @JoinColumn(name = "quickmenu"),
            inverseJoinColumns = @JoinColumn(name = "dishquickmenu"))
    private Set<DishQuickMenu> dishQuickMenu;

    //TODO сделать на dayofweek
    private DayOfWeek dayOfWeek;

    public QuickMenu(@NotBlank String name, Set<DishQuickMenu> dishQuickMenu, DayOfWeek dayOfWeek) {
        this.name = name;
        this.dishQuickMenu = dishQuickMenu;
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

    public Set<DishQuickMenu> getDishQuickMenu() {
        return dishQuickMenu;
    }

    public void setDishQuickMenu(Set<DishQuickMenu> dishQuickMenu) {
        this.dishQuickMenu = dishQuickMenu;
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
                ", dishQuickMenus=" + dishQuickMenu +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
