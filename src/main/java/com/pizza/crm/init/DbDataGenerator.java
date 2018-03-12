package com.pizza.crm.init;


import com.pizza.crm.model.*;
import com.pizza.crm.model.security.Role;
import com.pizza.crm.model.security.User;
import com.pizza.crm.service.*;
import com.pizza.crm.service.security.RoleService;
import com.pizza.crm.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class DbDataGenerator implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AddedCategoryService addedCategoryService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private QuickMenuService quickMenuService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleService.save(adminRole);
        roleService.save(userRole);

        userService.save(new User("admin", true, Arrays.asList(adminRole, userRole)));
        userService.save(new User("user", true, Collections.singletonList(userRole)));

        addedCategoryService.save(new AddedCategory("Pizza", "white"));
        addedCategoryService.save(new AddedCategory("Roll", "green"));

        Dish dishPizza = new Dish("Pizza margarita", 500, "1000", "9999", "10001", 1);
        Dish dishRol = new Dish("Roll philadelphia", 350, "1001", "9998", "10002", 2);
        Dish dishRol1 = new Dish("Roll california", 300, "1002", "9997", "10003", 3);
        Dish dishRol2 = new Dish("Roll dragon", 400, "1003", "9996", "10004",1);

        Ingredient ingredient = new Ingredient("Dough", 1.0, "1", "kg");

        ingredientService.save(ingredient);

        //dishPizza.addIngredient(ingredient);

        dishService.save(dishPizza);
        dishService.save(dishRol);
        dishService.save(dishRol1);
        dishService.save(dishRol2);

        categoryService.save(new Category("Pizza", new HashSet<>(Arrays.asList(dishPizza, dishRol))));
        categoryService.save(new Category("Roll", new HashSet<>(Arrays.asList(dishRol, dishRol1, dishRol2))));

        quickMenuService.save(new QuickMenu("Roll", new HashSet<>(Arrays.asList(dishPizza, dishRol)), 1));
        quickMenuService.save(new QuickMenu("Pizza", new HashSet<>(Arrays.asList(dishPizza, dishRol2)), 1));
        quickMenuService.save(new QuickMenu("Test", new HashSet<>(Arrays.asList(dishPizza, dishRol2)), 1));
    }
}
