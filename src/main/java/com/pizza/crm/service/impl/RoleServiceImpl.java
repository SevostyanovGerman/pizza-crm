package com.pizza.crm.service.impl;

import com.pizza.crm.config.security.Role;
import com.pizza.crm.repository.RoleRepository;
import com.pizza.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

//TODO переместить туда где и все сервисы
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Collection<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Collection<Role> saveAll(Collection<Role> roles) {
        return roleRepository.saveAll(roles);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
