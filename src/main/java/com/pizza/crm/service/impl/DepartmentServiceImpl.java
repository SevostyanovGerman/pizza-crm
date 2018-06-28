package com.pizza.crm.service.impl;

import com.pizza.crm.model.Department;
import com.pizza.crm.repository.DepartmentRepository;
import com.pizza.crm.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Collection<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department save(Department department) {
        LOGGER.debug("Saving {}", department);
        return departmentRepository.save(department);
    }

    @Override
    public Collection<Department> saveAll(Collection<Department> departments) {
        LOGGER.debug("Saving all {}", departments);
        return departmentRepository.saveAll(departments);
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.debug("Deleting by id {}", id);
        departmentRepository.deleteById(id);
    }
}
