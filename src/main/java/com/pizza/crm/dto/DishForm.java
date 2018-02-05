package com.pizza.crm.dto;

import java.util.List;

public class DishForm {

    private Long id;
    private String name;
    private List<Long> categories;

    public DishForm() {
    }

    public DishForm(Long id, String name, List<Long> categories) {
        this.id = id;
        this.name = name;
        this.categories = categories;
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


    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }
}
