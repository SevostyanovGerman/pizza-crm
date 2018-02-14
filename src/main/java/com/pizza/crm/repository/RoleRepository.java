package com.pizza.crm.repository;

import com.pizza.crm.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
