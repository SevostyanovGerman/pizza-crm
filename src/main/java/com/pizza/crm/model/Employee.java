package com.pizza.crm.model;


import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    //TODO убрать это поле
    @NotBlank
    private String login;

    //TODO убрать это поле
    private String password;

    //TODO убрать это поле мы связываем работника с юзером 1 к 1
    @NotBlank
    private String pincode;

    private Boolean dismissed = false;

    @Embedded
    private EmployeeInfo employeeInfo;

    @Embedded
    private Address address;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Employee_Position",
            joinColumns = @JoinColumn(name = "employee"),
            inverseJoinColumns = @JoinColumn(name = "position"))
    @JsonBackReference
    private Set<Position> positions = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Employee_Department",
            joinColumns = @JoinColumn(name = "employee"),
            inverseJoinColumns = @JoinColumn(name = "department"))
    @JsonBackReference
    private Set<Department> departments = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Order> orders = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Employee_Cooking",
            joinColumns = @JoinColumn(name = "employee"),
            inverseJoinColumns = @JoinColumn(name = "cooking"))
    private Set<Cooking> cooking = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Employee_QualityControl",
            joinColumns = @JoinColumn(name = "employee"),
            inverseJoinColumns = @JoinColumn(name = "qualityControl"))
    private Set<QualityControl> qualityControl = new HashSet<>();

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<DeliveryOrder> deliveryOrder = new ArrayList<>();

    public Employee(@NotBlank String name, @NotBlank String login, String password, @NotBlank String pincode,
                    Boolean dismissed, EmployeeInfo employeeInfo, Address address, Set<Position> positions,
                    Set<Department> departments, Collection<Order> orders, Set<Cooking> cooking,
                    Set<QualityControl> qualityControl, Collection<DeliveryOrder> deliveryOrder) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.pincode = pincode;
        this.dismissed = dismissed;
        this.employeeInfo = employeeInfo;
        this.address = address;
        this.positions = positions;
        this.departments = departments;
        this.orders = orders;
        this.cooking = cooking;
        this.qualityControl = qualityControl;
        this.deliveryOrder = deliveryOrder;
    }

    public Employee(@NotBlank String name, @NotBlank String login, String password, @NotBlank String pincode) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.pincode = pincode;
        this.employeeInfo = new EmployeeInfo();
        this.address = new Address();
    }

    public Employee(@NotBlank String name, String password, @NotBlank String pincode) {
        this(name, name, password, pincode);
    }

    public Employee(@NotBlank String name, @NotBlank String pincode) {
        this(name, name, "", pincode);
    }

    public Employee(@NotBlank String name) {
        this(name, name, "", name);
    }

    public Employee() {
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

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public Collection<DeliveryOrder> getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(Collection<DeliveryOrder> deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public Set<Cooking> getCooking() {
        return cooking;
    }

    public void setCooking(Set<Cooking> cooking) {
        this.cooking = cooking;
    }

    public Set<QualityControl> getQualityControl() {
        return qualityControl;
    }

    public void setQualityControl(Set<QualityControl> qualityControl) {
        this.qualityControl = qualityControl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Boolean getDismissed() {
        return dismissed;
    }

    public void setDismissed(Boolean dismissed) {
        this.dismissed = dismissed;
    }

    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    public void addPosition(Position position) {
        getPositions().add(position);
        position.getEmployees().add(this);
    }

    public void removePosition(Position position) {
        getPositions().remove(position);
        position.getEmployees().remove(this);
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public void addDepartment(Department department) {
        getDepartments().add(department);
        department.getEmployees().add(this);
    }

    public void removeDepartment(Department department) {
        getDepartments().remove(department);
        department.getEmployees().remove(this);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", pincode='" + pincode + '\'' +
                ", dismissed=" + dismissed +
                '}';
    }
}
