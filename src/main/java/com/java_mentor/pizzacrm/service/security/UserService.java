package com.java_mentor.pizzacrm.service.security;

import com.java_mentor.pizzacrm.model.security.User;
import com.java_mentor.pizzacrm.service.CrudService;

import java.util.Optional;

public interface UserService extends CrudService<User, Integer> {

    Optional<User> findByUsername(String username);

}
