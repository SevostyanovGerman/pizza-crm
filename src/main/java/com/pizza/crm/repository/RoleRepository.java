package com.pizza.crm.repository;

import com.pizza.crm.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getByName(String name);
}
