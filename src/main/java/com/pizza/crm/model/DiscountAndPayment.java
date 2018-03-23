package com.pizza.crm.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DiscountAndPayment")
public class DiscountAndPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private Set<String> paymentTypes;

    public DiscountAndPayment() {
        paymentTypes = new HashSet<>();
        paymentTypes.add("paymentByCard");
        paymentTypes.add("diners");
        paymentTypes.add("masterCardElectronics");
        paymentTypes.add("visa");
        paymentTypes.add("masterCard");
        paymentTypes.add("visaElectron");
        paymentTypes.add("maestro");
        paymentTypes.add("cash");
        paymentTypes.add("onlinePayment");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(Set<String> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }
}
