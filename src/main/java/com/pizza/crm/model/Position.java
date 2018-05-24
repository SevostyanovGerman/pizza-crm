package com.pizza.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String shortName;

    private String comment;
    private Boolean enabled;

    @ManyToMany(mappedBy = "positions")
    @JsonBackReference
    private Set<Employee> employees = new HashSet<>();

    public Position(@NotBlank String name, @NotBlank String shortName, String comment, Boolean enabled) {
        this.name = name;
        this.shortName = shortName;
        this.comment = comment;
        this.enabled = enabled;
    }

    public Position(@NotBlank String name, @NotBlank String shortName, String comment) {
        this(name, shortName, comment, true);
    }

    public Position(@NotBlank String name, @NotBlank String shortName) {
        this(name, shortName, "", true);
    }

    public Position() {
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return name;
    }
}
