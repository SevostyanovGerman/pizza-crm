package com.pizza.crm.service;

import com.pizza.crm.model.Employee;

import java.util.Optional;

public interface EmployeeService extends CrudService<Employee, Long> {

    Optional<Employee> findByPincode(String pincode);

}
