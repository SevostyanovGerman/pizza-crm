package com.pizza.crm.init;


import com.pizza.crm.model.AddedCategory;
import com.pizza.crm.model.Category;
import com.pizza.crm.model.Dish;
import com.pizza.crm.model.Ingredient;
import com.pizza.crm.model.security.Role;
import com.pizza.crm.model.security.User;
import com.pizza.crm.service.AddedCategoryService;
import com.pizza.crm.service.CategoryService;
import com.pizza.crm.service.DishService;
import com.pizza.crm.service.IngredientService;
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

        Dish dishPizza = new Dish("Pizza margarita", 500);
        Dish dishRol = new Dish("Roll philadelphia", 350);
        Dish dishRol1 = new Dish("Roll california", 300);
        Dish dishRol2 = new Dish("Roll dragon", 400);

        Ingredient ingredient = new Ingredient("Dough", 1.0, "1", "kg");
        ingredientService.save(ingredient);

        dishPizza.addIngredient(ingredient);

        dishService.save(dishPizza);
        dishService.save(dishRol);
        dishService.save(dishRol1);
        dishService.save(dishRol2);

        categoryService.save(new Category("Pizza", new HashSet<>(Arrays.asList(dishPizza, dishRol))));
        categoryService.save(new Category("Roll", new HashSet<>(Arrays.asList(dishRol, dishRol1, dishRol2))));

    }
}
