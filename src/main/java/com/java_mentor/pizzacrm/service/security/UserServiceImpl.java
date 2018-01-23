package com.java_mentor.pizzacrm.service.security;

import com.java_mentor.pizzacrm.model.security.User;
import com.java_mentor.pizzacrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Collection<User> getAll() {

        Collection<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;

    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public User save(User user) {

        if (user.getPassword() != null) {
            user.setEncryptedPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

}
