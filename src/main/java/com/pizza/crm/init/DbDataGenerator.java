package com.pizza.crm.init;

import com.pizza.crm.model.Categories;
import com.pizza.crm.model.Dish;
import com.pizza.crm.model.security.Role;
import com.pizza.crm.model.security.User;
import com.pizza.crm.service.security.CategoriesService;
import com.pizza.crm.service.security.DishService;
import com.pizza.crm.service.security.RoleService;
import com.pizza.crm.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DbDataGenerator implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private DishService dishService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleService.save(adminRole);
        roleService.save(userRole);

        userService.save(new User("admin", true, Arrays.asList(adminRole, userRole)));
        userService.save(new User("user", true, Collections.singletonList(userRole)));

        Dish dishPizza = new Dish("Pizza margarita");
        Dish dishRol = new Dish("Rol Folodelfia");

        dishService.save(dishPizza);
        dishService.save(dishRol);

        categoriesService.save(new Categories("Pizza", new HashSet<>(Arrays.asList(dishPizza))));
        categoriesService.save(new Categories("Rol"));
    }
}
