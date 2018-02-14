package com.pizza.crm.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Invoice> invoice = new ArrayList<>();

    @ManyToMany(mappedBy = "stock")
    private Set<SalesPoint> salesPoint = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Stock_ResidueRegister",
            joinColumns = @JoinColumn(name = "stock"),
            inverseJoinColumns = @JoinColumn(name = "residueRegister"))
    private Set<ResidueRegister> residueRegister = new HashSet<>();

    public Stock() {
    }

    public Stock(String name) {
        this.name = name;
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

    public Collection<Invoice> getInvoice() {
        return invoice;
    }

    public void setInvoice(Collection<Invoice> invoice) {
        this.invoice = invoice;
    }

    public Set<SalesPoint> getSalesPoint() {
        return salesPoint;
    }

    public void setSalesPoint(Set<SalesPoint> salesPoint) {
        this.salesPoint = salesPoint;
    }

    public Set<ResidueRegister> getResidueRegister() {
        return residueRegister;
    }

    public void setResidueRegister(Set<ResidueRegister> residueRegister) {
        this.residueRegister = residueRegister;
    }
}
