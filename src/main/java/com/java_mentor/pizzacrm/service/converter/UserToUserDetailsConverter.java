package com.java_mentor.pizzacrm.service.converter;

import com.java_mentor.pizzacrm.model.security.User;
import com.java_mentor.pizzacrm.service.security.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserToUserDetailsConverter implements Converter<User, UserDetails> {

    @Override
    public UserDetails convert(User user) {

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new UserDetailsImpl()
                .setUsername(user.getUsername())
                .setPassword(user.getEncryptedPassword())
                .setEnabled(user.getEnabled())
                .setAuthorities(authorities);

    }
}
