package com.pizza.crm.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Decree")
public class Decree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numberDecree;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String comment;

    private String nameForIikoFront;

    private Boolean enable;

    @ManyToMany
    @JoinTable(name = "Dish_Dicree",
            joinColumns = @JoinColumn(name = "decree"),
            inverseJoinColumns = @JoinColumn(name = "dish"))
    private Set<Dish> dishes;

    public Decree() {

    }

    public Decree(String numberDecree, LocalDateTime startTime, LocalDateTime endTime, String comment, String nameForIikoFront, Boolean enable) {
        this.numberDecree = numberDecree;
        this.startTime = startTime;
        this.endTime = endTime;
        this.comment = comment;
        this.nameForIikoFront = nameForIikoFront;
        this.enable = enable;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberDecree() {
        return numberDecree;
    }

    public void setNumberDecree(String numberDecree) {
        this.numberDecree = numberDecree;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNameForIikoFront() {
        return nameForIikoFront;
    }

    public void setNameForIikoFront(String nameForIikoFront) {
        this.nameForIikoFront = nameForIikoFront;
    }

    @Override
    public String toString() {
        return "Decree{" +
                "id=" + id +
                ", numberDecree='" + numberDecree + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", comment='" + comment + '\'' +
                ", nameForIikoFront='" + nameForIikoFront + '\'' +
                '}';
    }
}
