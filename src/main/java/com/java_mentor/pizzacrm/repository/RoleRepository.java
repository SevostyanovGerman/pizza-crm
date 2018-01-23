package com.java_mentor.pizzacrm.repository;

import com.java_mentor.pizzacrm.model.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
