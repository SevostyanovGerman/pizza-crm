package com.pizza.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PaymentType")
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "paymentType", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<PaymentMethod> paymentMethod = new HashSet<>();

    public PaymentType(String name) {
        this.name = name;
    }

    public PaymentType() {
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

    public Set<PaymentMethod> getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Set<PaymentMethod> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return name;
    }
}
