package com.pizza.crm.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "QuickMenu")
public class QuickMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany
    @JoinTable(name = "Dish_QuickMenu_more",
            joinColumns = @JoinColumn(name = "quickmenu"),
            inverseJoinColumns = @JoinColumn(name = "dishquickmenu"))
    private Set<DishQuickMenu> dishQuickMenu;

    private int weekDay;

    public QuickMenu(@NotBlank String name, Set<DishQuickMenu> dishQuickMenu, int weekDay) {
        this.name = name;
        this.dishQuickMenu = dishQuickMenu;
        this.weekDay = weekDay;
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

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public String toString() {
        return "QuickMenu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishQuickMenus=" + dishQuickMenu +
                ", weekDay=" + weekDay +
                '}';
    }
}
