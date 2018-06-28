package com.pizza.crm.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String addressRepresentation;
    private String postCode;
    private String region;
    private String city;
    private String street;
    private String apartmentNumber;

    public Address(String addressRepresentation, String postCode, String region, String city,
                   String street, String apartmentNumber) {
        this.addressRepresentation = addressRepresentation;
        this.postCode = postCode;
        this.region = region;
        this.city = city;
        this.street = street;
        this.apartmentNumber = apartmentNumber;
    }

    public Address(String addressRepresentation) {
        this(addressRepresentation, "", "", "", "", "");
    }

    public Address() {
    }

    public String getAddressRepresentation() {
        return addressRepresentation;
    }

    public void setAddressRepresentation(String addressRepresentation) {
        this.addressRepresentation = addressRepresentation;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressRepresentation='" + addressRepresentation + '\'' +
                ", postCode='" + postCode + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                '}';
    }
}
