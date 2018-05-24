package com.pizza.crm.model;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class EmployeeInfo {

    private String personnelNumber;
    private String firstName;
    private String surname;
    private String patronymic;
    private String sex;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String additionPhoneNumber;
    private String mobilePhoneNumber;
    private String email;
    private String cardNumber;

    public EmployeeInfo(String personnelNumber, String firstName, String surname, String patronymic, String sex,
                        LocalDate dateOfBirth, String phoneNumber, String additionPhoneNumber, String mobilePhoneNumber,
                        String email, String cardNumber) {
        this.personnelNumber = personnelNumber;
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.additionPhoneNumber = additionPhoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email = email;
        this.cardNumber = cardNumber;
    }

    public EmployeeInfo() {
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdditionPhoneNumber() {
        return additionPhoneNumber;
    }

    public void setAdditionPhoneNumber(String additionPhoneNumber) {
        this.additionPhoneNumber = additionPhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "EmployeeInfo{" +
                "personnelNumber='" + personnelNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", sex='" + sex + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", additionPhoneNumber='" + additionPhoneNumber + '\'' +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }

}
