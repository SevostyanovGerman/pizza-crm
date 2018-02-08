package com.pizza.crm.model;

import javax.persistence.*;

@Entity
@Table (name = "AddedCategoryRepository")
public class AddedCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String color;

    public AddedCategory(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public AddedCategory() {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
