package com.pizza.crm.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DishQuickMenu")
public class DishQuickMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String color;

    private int position;

    @ManyToMany
    @JoinTable(name = "DishQuickMenu_Dish",
            joinColumns = @JoinColumn(name = "dishQuickMenu"),
            inverseJoinColumns = @JoinColumn(name = "dish"))
    private Set<Dish> dish;

    public DishQuickMenu(String color, int position, Set<Dish> dish) {
        this.color = color;
        this.position = position;
        this.dish = dish;
    }

    public DishQuickMenu() {
    }

    public Long getId() {
        return id;
    }

    public Set<Dish> getDish() {
        return dish;
    }

    public void setDish(Set<Dish> dish) {
        this.dish = dish;
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
        return "DishQuickMenu{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", position=" + position +
                ", dish=" + dish +
                '}';
    }
}
