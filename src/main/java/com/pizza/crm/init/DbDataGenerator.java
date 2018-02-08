package com.pizza.crm.init;

import com.pizza.crm.model.Dish;
import com.pizza.crm.model.Category;
import com.pizza.crm.model.security.Role;
import com.pizza.crm.model.security.User;
import com.pizza.crm.service.CategoryService;
import com.pizza.crm.service.DishService;
import com.pizza.crm.service.security.RoleService;
import com.pizza.crm.service.security.UserService;
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

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleService.save(adminRole);
        roleService.save(userRole);

        userService.save(new User("admin", true, Arrays.asList(adminRole, userRole)));
        userService.save(new User("user", true, Collections.singletonList(userRole)));

        Category dc1 = new Category("Закуски");
        Category dc2 = new Category("Пицца");
        Category dc3 = new Category("Компоты/морсы");
        Category dc4 = new Category("Кофе/чай");
        Category dc5 = new Category("С собой");

        categoryService.saveAll(Arrays.asList(dc1, dc2, dc3, dc4, dc5));

        Dish d1 = new Dish("Крылья куриные");
        Dish d2 = new Dish("Чемпионская 35см");
        Dish d3 = new Dish("Барбекю 35см");

        d1.addDishCategory(dc1);

        d2.addDishCategory(dc2);

        d3.addDishCategory(dc2);

        dishService.saveAll(Arrays.asList(d1, d2, d3));

    }
}
