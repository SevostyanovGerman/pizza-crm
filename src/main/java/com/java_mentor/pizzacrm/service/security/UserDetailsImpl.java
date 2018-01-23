package com.java_mentor.pizzacrm.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private Collection<SimpleGrantedAuthority> authorities;
    private String username;
    private String password;
    private Boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "UserDetailsImpl{" +
                "authorities=" + authorities +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    public UserDetailsImpl setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public UserDetailsImpl setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDetailsImpl setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDetailsImpl setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

}
