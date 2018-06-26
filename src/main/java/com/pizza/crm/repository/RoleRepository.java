package com.pizza.crm.repository;

import com.pizza.crm.config.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
