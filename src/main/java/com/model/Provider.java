package com.model;

import javax.persistence.*;

@Entity(name="Provider")
@Table(name = "Provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "namePrint")
    private String namePrint;

    @Column(name = "inn")
    private String inn;

    @Column(name = "typeProvider")
    private TypeProvider typeProvider;

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

    public String getNamePrint() {
        return namePrint;
    }

    public void setNamePrint(String namePrint) {
        this.namePrint = namePrint;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public TypeProvider getTypeProvider() {
        return typeProvider;
    }

    public void setTypeProvider(TypeProvider typeProvider) {
        this.typeProvider = typeProvider;
    }
}
