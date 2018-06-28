package com.pizza.crm.service.impl;

import com.pizza.crm.config.security.User;
import com.pizza.crm.repository.UserRepository;
import com.pizza.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

//TODO переместить туда где и все сервисы
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Collection<User> saveAll(Collection<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByPincode(String pincode) {
        return Optional.ofNullable(userRepository.findByPincodeAndFetchRolesEagerly(pincode));
    }

}
