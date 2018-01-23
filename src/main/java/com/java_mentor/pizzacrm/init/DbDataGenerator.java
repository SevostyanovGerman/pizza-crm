package com.java_mentor.pizzacrm.init;

import com.java_mentor.pizzacrm.model.security.Role;
import com.java_mentor.pizzacrm.model.security.User;
import com.java_mentor.pizzacrm.service.security.RoleService;
import com.java_mentor.pizzacrm.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class DbDataGenerator implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleService.save(adminRole);

        Role userRole = new Role();
        userRole.setName("USER");
        roleService.save(userRole);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRoles(Arrays.asList(adminRole, userRole));
        userService.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword("");
        user.setRoles(Collections.singletonList(userRole));
        userService.save(user);

    }
}
