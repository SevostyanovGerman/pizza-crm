package com.pizza.crm.model;

import javax.persistence.*;

//TODO удалить и сервисы и контроллеры
@Entity
@Table(name = "Provider")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String namePrint;

    private String inn;

    @Enumerated(EnumType.STRING)
    private TypeProvider typeProvider;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice")
    private Invoice invoice;

    public Provider() {
    }

    public Provider(String name, String namePrint, String inn, TypeProvider typeProvider) {
        this.name = name;
        this.namePrint = namePrint;
        this.inn = inn;
        this.typeProvider = typeProvider;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
