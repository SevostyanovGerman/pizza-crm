package com.pizza.crm.repository;

import com.pizza.crm.model.security.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    User findByPincode(String pincode);

}
