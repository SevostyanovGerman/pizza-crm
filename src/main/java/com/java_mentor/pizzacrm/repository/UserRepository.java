package com.java_mentor.pizzacrm.repository;

import com.java_mentor.pizzacrm.model.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
