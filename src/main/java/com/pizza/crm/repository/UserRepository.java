package com.pizza.crm.repository;

import com.pizza.crm.config.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByPincode(String pincode);

}
