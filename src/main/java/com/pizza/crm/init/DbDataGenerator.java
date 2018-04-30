package com.pizza.crm.init;

import com.github.javafaker.Faker;
import com.pizza.crm.model.*;
import com.pizza.crm.model.discount.Discount;
import com.pizza.crm.model.discount.DiscountCategory;
import com.pizza.crm.model.discount.DiscountMode;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.pizza.crm.model.AccountingCategory.PRODUCT;
import static com.pizza.crm.model.NomenclatureType.DISH;
import static com.pizza.crm.model.NomenclatureType.MODIFIER;

@Component
public class DbDataGenerator implements ApplicationListener<ContextRefreshedEvent> {

    private final NomenclatureParentGroupService nomenclatureParentGroupService;

    private final NomenclatureService nomenclatureService;

    private final UserService userService;

    private final RoleService roleService;

    private final AddedCategoryService addedCategoryService;

    private final CategoryService categoryService;

    private final DishService dishService;

    private final IngredientService ingredientService;

    private final ScheduleService scheduleService;

    private final DiscountService discountService;

    private final DecreeService decreeService;

    private final QuickMenuService quickMenuService;

    private final DishQuickMenuService dishQuickMenuService;

    private final EmployeeService employeeService;

    private final PaymentMethodService paymentMethodService;

    private final PaymentTypeService paymentTypeService;

    private final UnitsOfMeasurementService unitsOfMeasurementService;

    private final ScaleOfSizeService scaleOfSizeService;

    @Autowired
    public DbDataGenerator(NomenclatureParentGroupService nomenclatureParentGroupService,
                           NomenclatureService nomenclatureService, UserService userService, RoleService roleService,
                           AddedCategoryService addedCategoryService, CategoryService categoryService,
                           DishService dishService, IngredientService ingredientService, ScheduleService scheduleService,
                           DiscountService discountService, DecreeService decreeService,
                           QuickMenuService quickMenuService, DishQuickMenuService dishQuickMenuService,
                           EmployeeService employeeService, PaymentMethodService paymentMethodService,
                           PaymentTypeService paymentTypeService, UnitsOfMeasurementService unitsOfMeasurementService, ScaleOfSizeService scaleOfSizeService) {
        this.nomenclatureParentGroupService = nomenclatureParentGroupService;
        this.nomenclatureService = nomenclatureService;
        this.userService = userService;
        this.roleService = roleService;
        this.addedCategoryService = addedCategoryService;
        this.categoryService = categoryService;
        this.dishService = dishService;
        this.ingredientService = ingredientService;
        this.scheduleService = scheduleService;
        this.discountService = discountService;
        this.decreeService = decreeService;
        this.quickMenuService = quickMenuService;
        this.dishQuickMenuService = dishQuickMenuService;
        this.employeeService = employeeService;
        this.paymentMethodService = paymentMethodService;
        this.paymentTypeService = paymentTypeService;
        this.unitsOfMeasurementService = unitsOfMeasurementService;
        this.scaleOfSizeService = scaleOfSizeService;
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

        String now = "2016-11-09 10:30";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);

        decreeService.save(new Decree("001", formatDateTime, formatDateTime, "123", "1111", false));

        DishQuickMenu dishQuickMenu1 = new DishQuickMenu("green", 1, new HashSet<>(Arrays.asList(dishPizza)));
        DishQuickMenu dishQuickMenu2 = new DishQuickMenu("red", 1, new HashSet<>(Arrays.asList(dishRol)));
        DishQuickMenu dishQuickMenu3 = new DishQuickMenu("red", 2, new HashSet<>(Arrays.asList(dishRol1)));
        DishQuickMenu dishQuickMenu4 = new DishQuickMenu("red", 2, new HashSet<>(Arrays.asList(dishRol2)));

        dishQuickMenuService.save(dishQuickMenu1);
        dishQuickMenuService.save(dishQuickMenu2);
        dishQuickMenuService.save(dishQuickMenu3);
        dishQuickMenuService.save(dishQuickMenu4);

        quickMenuService.save(new QuickMenu("|", new HashSet<>(), 1));
        quickMenuService.save(new QuickMenu("||", new HashSet<>(), 1));
        quickMenuService.save(new QuickMenu("|||", new HashSet<>(), 1));

        quickMenuService.save(new QuickMenu("|", new HashSet<>(), 2));
        quickMenuService.save(new QuickMenu("||", new HashSet<>(), 2));
        quickMenuService.save(new QuickMenu("|||", new HashSet<>(), 2));

        quickMenuService.save(new QuickMenu("|", new HashSet<>(), 3));
        quickMenuService.save(new QuickMenu("||", new HashSet<>(), 3));
        quickMenuService.save(new QuickMenu("|||", new HashSet<>(), 3));

        quickMenuService.save(new QuickMenu("|", new HashSet<>(), 4));
        quickMenuService.save(new QuickMenu("||", new HashSet<>(), 4));
        quickMenuService.save(new QuickMenu("|||", new HashSet<>(), 4));

        quickMenuService.save(new QuickMenu("|", new HashSet<>(), 5));
        quickMenuService.save(new QuickMenu("||", new HashSet<>(), 5));
        quickMenuService.save(new QuickMenu("|||", new HashSet<>(), 5));

        quickMenuService.save(new QuickMenu("|", new HashSet<>(), 6));
        quickMenuService.save(new QuickMenu("||", new HashSet<>(), 6));
        quickMenuService.save(new QuickMenu("|||", new HashSet<>(), 6));

        quickMenuService.save(new QuickMenu("Roll", new HashSet<>(Arrays.asList(dishQuickMenu1, dishQuickMenu2)), 7));
        quickMenuService.save(new QuickMenu("Pizza", new HashSet<>(Arrays.asList(dishQuickMenu1, dishQuickMenu3)), 7));
        quickMenuService.save(new QuickMenu("Test", new HashSet<>(Arrays.asList(dishQuickMenu4)), 7));

        Nomenclature philadelphia = new Nomenclature(15002, 370.00, LocalTime.of(0, 5, 15),
                LocalTime.of(0, 6, 30), "Филадельфия", DISH,
                PRODUCT, "Kitchen");
        Nomenclature california = new Nomenclature(15003, 430.00, LocalTime.of(0, 4, 15),
                LocalTime.of(0, 5, 30), "Калифорния", DISH,
                PRODUCT, "Kitchen");
        nomenclatureService.save(philadelphia);
        nomenclatureService.save(california);
        NomenclatureParentGroup rolls = new NomenclatureParentGroup("Роллы");
        rolls.setNomenclatures(new ArrayList<>(Arrays.asList(philadelphia, california)));
        nomenclatureParentGroupService.save(rolls);

        Nomenclature pineapple = new Nomenclature(15081, 50.00, null,
                null, "Ананас", MODIFIER,
                PRODUCT, "Kitchen");
        Nomenclature ham = new Nomenclature(15082, 50.00, null,
                null, "Ветчина", MODIFIER,
                PRODUCT, "Kitchen");
        Nomenclature mushrooms = new Nomenclature(15083, 50.00, null,
                null, "Грибы", MODIFIER,
                PRODUCT, "Kitchen");
        Nomenclature beackon = new Nomenclature(15084, 50.00, null,
                null, "Бекон", MODIFIER,
                PRODUCT, "Kitchen");
        Nomenclature kolbaski = new Nomenclature(15085, 50.00, null,
                null, "Колбаски", MODIFIER,
                PRODUCT, "Kitchen");
        nomenclatureService.save(pineapple);
        nomenclatureService.save(ham);
        nomenclatureService.save(mushrooms);
        nomenclatureService.save(beackon);
        nomenclatureService.save(kolbaski);
        NomenclatureParentGroup topings = new NomenclatureParentGroup("Топпинги");
        topings.setNomenclatures(new ArrayList<>(Arrays.asList(pineapple, ham)));
        nomenclatureParentGroupService.save(topings);

        Nomenclature margarita = new Nomenclature(15002, 370.00, LocalTime.of(0, 5, 15),
                LocalTime.of(0, 6, 30), "Маргарита", DISH,
                PRODUCT, "Kitchen");
        Nomenclature marinara = new Nomenclature(15003, 430.00, LocalTime.of(0, 4, 15),
                LocalTime.of(0, 5, 30), "Охотничая", DISH,
                PRODUCT, "Kitchen");

        nomenclatureService.save(margarita);
        nomenclatureService.save(marinara);
        NomenclatureParentGroup pizzas = new NomenclatureParentGroup("Пицца 35см");
        pizzas.setNomenclatures(new ArrayList<>(Arrays.asList(margarita, marinara)));
        nomenclatureParentGroupService.save(pizzas);

        UnitsOfMeasurement kg = new UnitsOfMeasurement("Киллограммы", "кг", true, 4747);
        UnitsOfMeasurement l = new UnitsOfMeasurement("Литры", "л", true, 4748);
        UnitsOfMeasurement portion = new UnitsOfMeasurement("Порция", "порц", true, 4749);
        unitsOfMeasurementService.save(kg);
        unitsOfMeasurementService.save(l);
        unitsOfMeasurementService.save(portion);

        generateFakeStaff();

        generateDiscountsAndPaymentMethods();

        //ScaleOfSize --------------------------------------------------------------------------------------------------
        ScaleOfSizeValues values = new ScaleOfSizeValues("0,33 литра" ,"0.3",true);
        ArrayList<ScaleOfSizeValues> list = new ArrayList<>(Arrays.asList(values));
        //ScaleOfSize scale = new ScaleOfSize("Обьем бутылок", list);
        ScaleOfSize scale = new ScaleOfSize("Обьем бутылок", list);
        scaleOfSizeService.save(scale);

    }

    private void generateDiscountsAndPaymentMethods() {
        PaymentType card = new PaymentType("Банковские карты");
        PaymentType cash = new PaymentType("Наличные");
        PaymentType withoutEarnings = new PaymentType("Безналичный расчет");
        PaymentType onHouse = new PaymentType("За счет заведения");
        paymentTypeService.saveAll(Arrays.asList(card, cash, withoutEarnings, onHouse));

        PaymentMethod pm1 = new PaymentMethod("Visa", card);
        PaymentMethod pm2 = new PaymentMethod("MasterCard", card);
        PaymentMethod pm3 = new PaymentMethod("Наличные", cash);
        PaymentMethod pm4 = new PaymentMethod("Безналичный расчет", withoutEarnings);
//        PaymentMethod pm5 = new PaymentMethod("За счет заведения", onHouse);
        paymentMethodService.saveAll(Arrays.asList(pm1, pm2, pm3, pm4));

        List<Category> categories = new ArrayList<>(categoryService.getAll());

        DiscountCategory discountCategory1 = new DiscountCategory();
        discountCategory1.setDiscountMode(DiscountMode.DISCOUNT);
        discountCategory1.setCategory(categories.get(0));

        DiscountCategory discountCategory2 = new DiscountCategory();
        discountCategory2.setDiscountMode(DiscountMode.EXTRA_PAY);
        discountCategory2.setCategory(categories.get(1));

        Discount discount = new Discount("Скидка");
        discount.setType("Скидки и надбавки");
        discount.setAutomatic(true);
        discount.setManualSelectWithOthers(true);
        discount.getPaymentMethods().add(pm1);
//        discount.getPaymentMethods().add(pm3);
        discount.setDiscountCategories(Arrays.asList(discountCategory1, discountCategory2));
        discount.getSchedules().add(new Schedule("Расписание скидки в обед", LocalTime.of(12, 0), LocalTime.of(13, 0)));
        discount.getSchedules().add(new Schedule("Расписание скидки вечером", LocalTime.of(14, 0), LocalTime.of(16, 0),
                true, false, true, false, true, false, true));
        discountService.save(discount);
        pm1.setDiscount(discount);
//        pm3.setDiscount(discount);
        paymentMethodService.saveAll(Arrays.asList(pm1, pm3));
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
            EmployeeInfo employeeInfo = new EmployeeInfo(
                    faker.bothify("??###"),
                    faker.name().firstName(),
                    faker.name().nameWithMiddle(),
                    faker.name().lastName(),
                    "",
                    LocalDate.now(),
                    faker.phoneNumber().phoneNumber(),
                    faker.phoneNumber().phoneNumber(),
                    faker.phoneNumber().cellPhone(),
                    faker.internet().emailAddress(),
                    faker.business().creditCardNumber());
            Address address = new Address(faker.address().fullAddress());
            Employee employee = new Employee(
                    faker.name().name(),
                    faker.name().name(),
                    faker.internet().password(20, 21),
                    faker.numerify("####"));
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
