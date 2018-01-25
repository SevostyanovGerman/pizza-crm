package com.pizza.crm.service.security;

import com.pizza.crm.model.security.User;
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        LOGGER.debug("Trying to find user with pincode: '{}'", s);

        User user = userService.findByPincode(s).orElseThrow(() -> new UsernameNotFoundException("Can't find: " + s));

        LOGGER.debug("Found user: {}", user);

        return user;

    }
}
