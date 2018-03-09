package com.pizza.crm.repository;

import com.pizza.crm.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByPincode(String pincode);

}
