package com.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity(name="Invoice")
@Table(name = "Invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany
    private Set<Provider> provider;

    private Stock stock;

    private SalesPoint salesPoint;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateCreate")
    private Date dateCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Provider> getProvider() {
        return provider;
    }

    public void setProvider(Set<Provider> provider) {
        this.provider = provider;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public SalesPoint getSalesPoint() {
        return salesPoint;
    }

    public void setSalesPoint(SalesPoint salesPoint) {
        this.salesPoint = salesPoint;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}
