package com.java_mentor.pizzacrm.service.security;

import com.java_mentor.pizzacrm.model.security.Role;
import com.java_mentor.pizzacrm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Collection<Role> getAll() {

        Collection<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;

    }

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(new Role());
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }
}
