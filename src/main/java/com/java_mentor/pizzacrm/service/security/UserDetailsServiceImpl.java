package com.java_mentor.pizzacrm.service.security;

import com.java_mentor.pizzacrm.model.security.User;
import com.java_mentor.pizzacrm.service.converter.UserToUserDetailsConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserToUserDetailsConverter userToUserDetailsConverter;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        LOGGER.debug("Loading user by username: {}", s);

        User user = userService.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("Can't find: " + s));

        LOGGER.debug("Found user: {}", user);

        return userToUserDetailsConverter.convert(user);

    }
}
