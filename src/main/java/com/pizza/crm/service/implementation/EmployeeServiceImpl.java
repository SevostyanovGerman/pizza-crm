package com.pizza.crm.service.implementation;

import com.pizza.crm.model.Employee;
import com.pizza.crm.repository.EmployeeRepository;
import com.pizza.crm.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Collection<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        LOGGER.debug("Saving {}", employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Collection<Employee> saveAll(Collection<Employee> employees) {
        LOGGER.debug("Saving all {}", employees);
        return employeeRepository.saveAll(employees);
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.debug("Deleting by id {}", id);
        employeeRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> findByPincode(String pincode) {
        return employeeRepository.findByPincode(pincode);
    }
}
