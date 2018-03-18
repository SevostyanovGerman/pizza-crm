package com.pizza.crm.init;


import com.github.javafaker.Faker;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DbDataGenerator implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;
    private final RoleService roleService;
    private final AddedCategoryService addedCategoryService;
    private final CategoryService categoryService;
    private final DishService dishService;
    private final IngredientService ingredientService;
    private final ScheduleService scheduleService;
    private final EmployeeService employeeService;

    @Autowired
    public DbDataGenerator(UserService userService, RoleService roleService, AddedCategoryService addedCategoryService,
                           CategoryService categoryService, DishService dishService, IngredientService ingredientService,
                           ScheduleService scheduleService, PositionService positionService, EmployeeService employeeService) {
        this.userService = userService;
        this.roleService = roleService;
        this.addedCategoryService = addedCategoryService;
        this.categoryService = categoryService;
        this.dishService = dishService;
        this.ingredientService = ingredientService;
        this.scheduleService = scheduleService;
        this.employeeService = employeeService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleService.save(adminRole);
        roleService.save(userRole);

        userService.save(new User("admin", true, Arrays.asList(adminRole, userRole)));
        userService.save(new User("123", true, Arrays.asList(adminRole, userRole)));
        userService.save(new User("user", true, Collections.singletonList(userRole)));

        addedCategoryService.save(new AddedCategory("Pizza", "white"));
        addedCategoryService.save(new AddedCategory("Roll", "green"));
        addedCategoryService.save(new AddedCategory("Topings", "blue"));

        Dish dishPizza = new Dish("Pizza margarita", 500, "1000", "9999", "10001");
        Dish dishRol = new Dish("Roll philadelphia", 350, "1001", "9998", "10002");
        Dish dishRol1 = new Dish("Roll california", 300, "1002", "9997", "10003");
        Dish dishRol2 = new Dish("Roll dragon", 400, "1003", "9996", "10004");
        Dish dish1 = new Dish("dish1", 250, "500", "600", "700");
        Dish dish2 = new Dish("dish2", 250, "501", "601", "701");
        Dish dish3 = new Dish("dish3", 250, "502", "602", "702");
        Dish dish4 = new Dish("dish4", 250, "503", "603", "703");
        Dish dish5 = new Dish("dish5", 250, "504", "604", "704");
        Dish dish6 = new Dish("dish6", 250, "505", "605", "705");
        Dish dish7 = new Dish("dish7", 250, "506", "606", "706");
        Dish dish8 = new Dish("dish8", 250, "507", "607", "707");
        Dish dish9 = new Dish("dish9", 250, "508", "608", "708");
        Dish dish10 = new Dish("dish10", 250, "509", "609", "709");
        Dish dish11 = new Dish("dish11", 250, "510", "610", "710");
        Dish dish12 = new Dish("dish12", 250, "511", "611", "711");
        Dish dish13 = new Dish("dish13", 250, "512", "612", "712");
        Dish dish14 = new Dish("dish14", 250, "513", "613", "713");
        Dish dish15 = new Dish("dish15", 250, "514", "614", "714");
        Dish dish16 = new Dish("dish16", 250, "515", "615", "715");
        Dish dish17 = new Dish("dish17", 250, "516", "616", "716");
        Dish dish18 = new Dish("dish18", 250, "517", "617", "717");
        Dish dish19 = new Dish("dish19", 250, "518", "618", "718");
        Dish dish20 = new Dish("dish20", 250, "519", "619", "719");
        Dish dish21 = new Dish("dish21", 250, "520", "620", "720");
        Dish dish22 = new Dish("dish22", 250, "521", "621", "721");
        Dish dish23 = new Dish("dish23", 250, "522", "622", "722");
        Dish dish24 = new Dish("dish24", 250, "523", "623", "723");
        Dish dish25 = new Dish("dish25", 250, "524", "624", "724");

        Ingredient ingredient = new Ingredient("Dough", 1.0, "1", "kg");

        ingredientService.save(ingredient);

        //dishPizza.addIngredient(ingredient);

        dishService.save(dishPizza);
        dishService.save(dishRol);
        dishService.save(dishRol1);
        dishService.save(dishRol2);
        dishService.save(dish1);
        dishService.save(dish2);
        dishService.save(dish3);
        dishService.save(dish4);
        dishService.save(dish5);
        dishService.save(dish6);
        dishService.save(dish7);
        dishService.save(dish8);
        dishService.save(dish9);
        dishService.save(dish10);
        dishService.save(dish11);
        dishService.save(dish12);
        dishService.save(dish13);
        dishService.save(dish14);
        dishService.save(dish15);
        dishService.save(dish16);
        dishService.save(dish17);
        dishService.save(dish18);
        dishService.save(dish19);
        dishService.save(dish20);
        dishService.save(dish21);
        dishService.save(dish22);
        dishService.save(dish23);
        dishService.save(dish24);
        dishService.save(dish25);

        categoryService.save(new Category("Pizza", new HashSet<>(Arrays.asList(dishPizza, dishRol))));
        categoryService.save(new Category("Roll", new HashSet<>(Arrays.asList(dishRol, dishRol1, dishRol2))));
        categoryService.save(new Category("Topings", new HashSet<>(Arrays.asList(dish1, dish2, dish3, dish4,
                dish5, dish6, dish7, dish8, dish9, dish10, dish11, dish12, dish13, dish14, dish15, dish16, dish17,
                dish18, dish19, dish20, dish21, dish22, dish23, dish24, dish25))));

        scheduleService.save(new Schedule("Lunch", LocalTime.of(12, 00), LocalTime.of(13, 00), true, true, true, true, true, false, false));
        scheduleService.save(new Schedule("Dinner", LocalTime.of(18, 00), LocalTime.of(19, 00), false, false, false, false, false, true, true));

        generateFakeStaff();

    }

    private void generateFakeStaff() {
        Faker faker = new Faker(new Locale("ru"));

        List<Department> fakeDepartments = Stream.generate(() ->
                new Department(faker.commerce().department()))
                .limit(7)
                .collect(Collectors.toList());

        List<Position> fakePositions = Stream.generate(() ->
                new Position(faker.job().position(), faker.bothify("??###")))
                .limit(7)
                .collect(Collectors.toList());

        List<Employee> fakeEmployees = Stream.generate(() -> {
            EmployeeInfo employeeInfo = new EmployeeInfo(faker.bothify("??###"), faker.name().firstName(),
                    faker.name().nameWithMiddle(), faker.name().lastName(), "", LocalDate.now(),
                    faker.phoneNumber().phoneNumber(), faker.phoneNumber().phoneNumber(), faker.phoneNumber().cellPhone(),
                    faker.internet().emailAddress(), faker.business().creditCardNumber());
            Address address = new Address(faker.address().fullAddress());
            Employee employee = new Employee(faker.name().name(), faker.letterify("???"), faker.numerify("####"));
            employee.setEmployeeInfo(employeeInfo);
            employee.setAddress(address);
            return employee;
        })
                .limit(10)
                .collect(Collectors.toList());
        fakeEmployees.add(new Employee("admin", "admin", "admin"));

        Random random = new Random();
        fakeEmployees.forEach((e) -> {
            Department department = fakeDepartments.get(random.nextInt(fakeDepartments.size()));
            e.addDepartment(department);
            Position position = fakePositions.get(random.nextInt(fakePositions.size()));
            e.addPosition(position);
        });
        employeeService.saveAll(fakeEmployees);
    }
}
