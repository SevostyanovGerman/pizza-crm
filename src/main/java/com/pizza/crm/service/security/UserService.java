package com.pizza.crm.service.security;

import com.pizza.crm.model.security.User;
import com.pizza.crm.service.CrudService;

import java.util.Optional;

public interface UserService extends CrudService<User, Long> {

    Optional<User> findByPincode(String pincode);

}
