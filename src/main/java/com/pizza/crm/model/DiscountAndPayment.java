package com.pizza.crm.model;

import javax.persistence.*;

@Entity
@Table(name = "DiscountAndPayment")
public class DiscountAndPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean paymentByCard;

    private Boolean diners;

    private Boolean masterCardElectronics;

    private Boolean visa;

    private Boolean masterCard;

    private Boolean visaElectron;

    private Boolean maestro;

    private Boolean cash;

    private Boolean onlinePayment;

    public DiscountAndPayment() {
        paymentByCard = true;
        diners = true;
        masterCardElectronics = true;
        visa = true;
        masterCard = true;
        visaElectron = true;
        maestro = true;
        cash = true;
        onlinePayment = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPaymentByCard() {
        return paymentByCard;
    }

    public void setPaymentByCard(Boolean paymentByCard) {
        this.paymentByCard = paymentByCard;
    }

    public Boolean getDiners() {
        return diners;
    }

    public void setDiners(Boolean diners) {
        this.diners = diners;
    }

    public Boolean getMasterCardElectronics() {
        return masterCardElectronics;
    }

    public void setMasterCardElectronics(Boolean masterCardElectronics) {
        this.masterCardElectronics = masterCardElectronics;
    }

    public Boolean getVisa() {
        return visa;
    }

    public void setVisa(Boolean visa) {
        this.visa = visa;
    }

    public Boolean getMasterCard() {
        return masterCard;
    }

    public void setMasterCard(Boolean masterCard) {
        this.masterCard = masterCard;
    }

    public Boolean getVisaElectron() {
        return visaElectron;
    }

    public void setVisaElectron(Boolean visaElectron) {
        this.visaElectron = visaElectron;
    }

    public Boolean getMaestro() {
        return maestro;
    }

    public void setMaestro(Boolean maestro) {
        this.maestro = maestro;
    }

    public Boolean getCash() {
        return cash;
    }

    public void setCash(Boolean cash) {
        this.cash = cash;
    }

    public Boolean getOnlinePayment() {
        return onlinePayment;
    }

    public void setOnlinePayment(Boolean onlinePayment) {
        this.onlinePayment = onlinePayment;
    }
}
