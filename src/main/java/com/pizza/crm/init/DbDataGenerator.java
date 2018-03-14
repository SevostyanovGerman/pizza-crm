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

    @Autowired
    private DishQuickMenuService dishQuickMenuService;

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

        Dish dishPizza = new Dish("Pizza margarita", 500, "1000", "9999", "10001");
        Dish dishRol = new Dish("Roll philadelphia", 350, "1001", "9998", "10002");
        Dish dishRol1 = new Dish("Roll california", 300, "1002", "9997", "10003");
        Dish dishRol2 = new Dish("Roll dragon", 400, "1003", "9996", "10004");

        Ingredient ingredient = new Ingredient("Dough", 1.0, "1", "kg");

        ingredientService.save(ingredient);

        //dishPizza.addIngredient(ingredient);

        dishService.save(dishPizza);
        dishService.save(dishRol);
        dishService.save(dishRol1);
        dishService.save(dishRol2);

        categoryService.save(new Category("Pizza", new HashSet<>(Arrays.asList(dishPizza, dishRol))));
        categoryService.save(new Category("Roll", new HashSet<>(Arrays.asList(dishRol, dishRol1, dishRol2))));

        DishQuickMenu dishQuickMenu1 = new DishQuickMenu("green", 1, new HashSet<>(Arrays.asList(dishPizza)));
        DishQuickMenu dishQuickMenu2 = new DishQuickMenu("red", 1, new HashSet<>(Arrays.asList(dishPizza)));
        DishQuickMenu dishQuickMenu3 = new DishQuickMenu("red", 2, new HashSet<>(Arrays.asList(dishPizza)));
        DishQuickMenu dishQuickMenu4 = new DishQuickMenu("red", 2, new HashSet<>(Arrays.asList(dishPizza)));

        dishQuickMenuService.save(dishQuickMenu1);
        dishQuickMenuService.save(dishQuickMenu2);
        dishQuickMenuService.save(dishQuickMenu3);
        dishQuickMenuService.save(dishQuickMenu4);

        quickMenuService.save(new QuickMenu("Roll", new HashSet<>(Arrays.asList(dishQuickMenu1, dishQuickMenu2)), 1));
        quickMenuService.save(new QuickMenu("Pizza", new HashSet<>(Arrays.asList(dishQuickMenu1, dishQuickMenu3)), 1));
        quickMenuService.save(new QuickMenu("Test", new HashSet<>(Arrays.asList(dishQuickMenu4)), 1));

        quickMenuService.save(new QuickMenu("|", new HashSet<>(), 2));
        quickMenuService.save(new QuickMenu("||", new HashSet<>(), 2));
        quickMenuService.save(new QuickMenu("|||", new HashSet<>(), 2));

    }
}
