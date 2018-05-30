package com.pizza.crm.init;

import com.github.javafaker.Faker;
import com.pizza.crm.model.*;
import com.pizza.crm.model.discount.*;
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

    private final ValidityScheduleService validityScheduleService;

    private final ValidityService validityService;

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
                           DishService dishService, IngredientService ingredientService, ValidityScheduleService validityScheduleService,
                           ValidityService validityService, DiscountService discountService, DecreeService decreeService,
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
        this.validityScheduleService = validityScheduleService;
        this.validityService = validityService;
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

        generateDishes();

        generateValidity();

        generateFakeStaff();

        generateDiscountsAndPaymentMethods();

        generateUsers();

        generateAddedCategory();

        generateDecree();

        generateNomenclatureAndNomenclatureParentGroup();

        generateIngredient();

        generateUnitsOfMeasurement();

        generateScaleOfSize();
        
       

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

    private void generateDishes(){
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
    }

    private void generateUsers() {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleService.save(adminRole);
        roleService.save(userRole);

        userService.save(new User("admin", true, Arrays.asList(adminRole, userRole)));
        userService.save(new User("123", true, Arrays.asList(adminRole, userRole)));
        userService.save(new User("user", true, Collections.singletonList(userRole)));
    }

    private void generateAddedCategory() {
        addedCategoryService.save(new AddedCategory("Pizza", "white"));
        addedCategoryService.save(new AddedCategory("Roll", "green"));
        addedCategoryService.save(new AddedCategory("Topings", "blue"));
    }

    private void generateDecree() {
        String now = "2016-11-09 10:30";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);

        decreeService.save(new Decree("001", formatDateTime, formatDateTime, "123", "1111", false));

    }

    private void generateNomenclatureAndNomenclatureParentGroup() {
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
        Nomenclature tomat = new Nomenclature(15323, 05.00, null,
                null, "Помидор", MODIFIER,
                PRODUCT, "Kitchen");
        Nomenclature salami = new Nomenclature(16382, 20.00, null,
                null, "Салями", MODIFIER,
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
        nomenclatureService.save(tomat);
        nomenclatureService.save(salami);
        nomenclatureService.save(pineapple);
        nomenclatureService.save(ham);
        nomenclatureService.save(mushrooms);
        nomenclatureService.save(beackon);
        nomenclatureService.save(kolbaski);

        NomenclatureParentGroup topings = new NomenclatureParentGroup("Топпинги");
        topings.setNomenclatures(new ArrayList<>(Arrays.asList(pineapple, ham)));
        nomenclatureParentGroupService.save(topings);

        NomenclatureParentGroup omelet = new NomenclatureParentGroup("Омлет");
        omelet.setNomenclatures(new ArrayList<>(Arrays.asList(tomat, salami)));
        nomenclatureParentGroupService.save(omelet);

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
    }

    private void generateScaleOfSize(){
		ScaleOfSizeValues valuesDrink = new ScaleOfSizeValues("0,33 литра" ,"0.3 л.",false);
        ScaleOfSizeValues valuesPizza = new ScaleOfSizeValues("30 сантиметров" ,"30 см.",true);
        ScaleOfSizeValues valuesWeight = new ScaleOfSizeValues("100 грамм" ,"100 гр.",true);
        ArrayList<ScaleOfSizeValues> listDrink = new ArrayList<>(Arrays.asList(valuesDrink));
        ArrayList<ScaleOfSizeValues> listPizza = new ArrayList<>(Arrays.asList(valuesPizza));
        ArrayList<ScaleOfSizeValues> listWeight = new ArrayList<>(Arrays.asList(valuesWeight));
        ScaleOfSize scaleDrink = new ScaleOfSize("Обьем бутылок", listDrink);
        ScaleOfSize scalePizza = new ScaleOfSize("Размер пиццы", listPizza);
        ScaleOfSize scaleWeight = new ScaleOfSize("Вес товара", listWeight);
        scaleOfSizeService.save(scaleDrink);
        scaleOfSizeService.save(scalePizza);
        scaleOfSizeService.save(scaleWeight);
    }

    private void generateIngredient() {
        Ingredient ingredient = new Ingredient("Dough", 1.0, "1", "kg");

        ingredientService.save(ingredient);

        //dishPizza.addIngredient(ingredient);
    }

    private void generateValidity() {
        ValiditySchedule validityScheduleLunch = new ValiditySchedule(/*"Lunch",*/ LocalTime.of(12, 00), LocalTime.of(13, 00), true, true, true, true, true, false, false);
        ValiditySchedule validityScheduleLunch2 = new ValiditySchedule(/*"Lunch",*/ LocalTime.of(14, 30), LocalTime.of(15, 00), false, true, true, true, true, true, false);
        ArrayList<ValiditySchedule> listValidityScheduleLunches = new ArrayList<>(Arrays.asList(validityScheduleLunch, validityScheduleLunch2));

        ValiditySchedule validityScheduleDinner = new ValiditySchedule(/*"Dinner",*/ LocalTime.of(18, 00), LocalTime.of(20, 00), false, true, true, true, true, true, false);
        ArrayList<ValiditySchedule> listValidityScheduleDinner = new ArrayList<>(Arrays.asList(validityScheduleDinner));

        Validity validityDinner = new Validity("Dinner", listValidityScheduleDinner);
        Validity validityLunch = new Validity("Lunch", listValidityScheduleLunches);
        validityService.save(validityLunch);
        validityService.save(validityDinner);

        ValiditySchedule tuesdaySchedule = new ValiditySchedule(LocalTime.of(1, 00),
                LocalTime.of(23, 00), false, true, false, false,
                false, false, false);

        ArrayList<ValiditySchedule> listtuesdaySchedule =
                new ArrayList<>(Arrays.asList(tuesdaySchedule));

        Validity validitytuesday = new Validity("Вторник, с 1-00 до 23-00", listtuesdaySchedule);
        validityService.save(validitytuesday);

    }

    private void generateDiscountsAndPaymentMethods() {
        // PaymentType creating
        PaymentType card = new PaymentType("Банковские карты");
        PaymentType cash = new PaymentType("Наличные");
        PaymentType withoutEarnings = new PaymentType("Безналичный расчет");
        PaymentType onHouse = new PaymentType("За счет заведения");
        paymentTypeService.saveAll(Arrays.asList(card, cash, withoutEarnings, onHouse));

        // PaymentMethod creating
        PaymentMethod pm1 = new PaymentMethod("Visa", card);
        PaymentMethod pm2 = new PaymentMethod("MasterCard", card);
        PaymentMethod pm3 = new PaymentMethod("Наличные", cash);
        PaymentMethod pm4 = new PaymentMethod("Безналичный расчет", withoutEarnings);
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(pm1);
        paymentMethods.add(pm2);
        paymentMethods.add(pm3);
        paymentMethods.add(pm4);
        paymentMethodService.saveAll(paymentMethods);

        Discount discount = new Discount("Автоматическая скидка на все позиции");
        discount.setEnabled(true);
        discount.setValue(5);
        discount.setType("Скидки и надбавки");
        discount.setPaymentMethods((List<PaymentMethod>) paymentMethodService.getAll());
        discount.setMinSum(0d);
        discount.setMinSumRestriction(false);
        discount.setScheduleRestriction(false);
        discount.setManualSelectWithOthers(false);
        discount.setManualInput(false);
        discount.setManualDishSelect(false);
        discount.setAutomatic(true);
        discount.setCombinable(true);
        discount.setApplyForAllDiscountCategories(true);
        discount.setDetailWhenPrinting(false);
        discount.setPriority(0);
        discount.setComment("");
        discount.setDiscountApplicationMethod(DiscountApplicationMethod.FULL_PRICE);
        discount.setDiscountMode(DiscountMode.DISCOUNT);
        discount.setDiscountAssignMode(DiscountAssignMode.FIRST_DISH);
        discount.setDiscountCalculationMode(DiscountCalculationMode.PERCENT);
        discount.setValidities(null);
        discount.setDiscountCategories(null);
        discountService.save(discount);

        Discount extraCharge = new Discount("Надбавка");
        extraCharge.setEnabled(true);
        extraCharge.setValue(10);
        extraCharge.setType("Скидки и надбавки");
        extraCharge.setPaymentMethods((List<PaymentMethod>) paymentMethodService.getAll());
        extraCharge.setMinSum(0d);
        extraCharge.setMinSumRestriction(false);
        extraCharge.setScheduleRestriction(false);
        extraCharge.setManualSelectWithOthers(false);
        extraCharge.setManualInput(false);
        extraCharge.setManualDishSelect(false);
        extraCharge.setAutomatic(false);
        extraCharge.setCombinable(true);
        extraCharge.setApplyForAllDiscountCategories(true);
        extraCharge.setDetailWhenPrinting(false);
        extraCharge.setPriority(0);
        extraCharge.setComment("");
        extraCharge.setDiscountApplicationMethod(DiscountApplicationMethod.FULL_PRICE);
        extraCharge.setDiscountMode(DiscountMode.EXTRA_PAY);
        extraCharge.setDiscountAssignMode(DiscountAssignMode.FIRST_DISH);
        extraCharge.setDiscountCalculationMode(DiscountCalculationMode.PERCENT);
        extraCharge.setValidities(null);
        extraCharge.setDiscountCategories(null);
        discountService.save(extraCharge);

        Discount defaultDiscount = new Discount("Скидка 15%");
        defaultDiscount.setEnabled(true);
        defaultDiscount.setValue(15);
        defaultDiscount.setType("Скидки и надбавки");
        defaultDiscount.setPaymentMethods((List<PaymentMethod>) paymentMethodService.getAll());
        defaultDiscount.setMinSum(0d);
        defaultDiscount.setMinSumRestriction(false);
        defaultDiscount.setScheduleRestriction(false);
        defaultDiscount.setManualSelectWithOthers(false);
        defaultDiscount.setManualInput(false);
        defaultDiscount.setManualDishSelect(false);
        defaultDiscount.setAutomatic(false);
        defaultDiscount.setCombinable(true);
        defaultDiscount.setApplyForAllDiscountCategories(true);
        defaultDiscount.setDetailWhenPrinting(false);
        defaultDiscount.setPriority(0);
        defaultDiscount.setComment("");
        defaultDiscount.setDiscountApplicationMethod(DiscountApplicationMethod.FULL_PRICE);
        defaultDiscount.setDiscountMode(DiscountMode.DISCOUNT);
        defaultDiscount.setDiscountAssignMode(DiscountAssignMode.FIRST_DISH);
        defaultDiscount.setDiscountCalculationMode(DiscountCalculationMode.PERCENT);
        defaultDiscount.setValidities(null);
        defaultDiscount.setDiscountCategories(null);
        discountService.save(defaultDiscount);

        Discount minSumDiscount =
                new Discount("Применяется от 2000 рублей с учетом других скидок (Не к полной сумме)");
        minSumDiscount.setEnabled(true);
        minSumDiscount.setValue(30);
        minSumDiscount.setType("Скидки и надбавки");
        minSumDiscount.setPaymentMethods((List<PaymentMethod>) paymentMethodService.getAll());
        minSumDiscount.setMinSum(2000d);
        minSumDiscount.setMinSumRestriction(true);
        minSumDiscount.setScheduleRestriction(false);
        minSumDiscount.setManualSelectWithOthers(false);
        minSumDiscount.setManualInput(false);
        minSumDiscount.setManualDishSelect(false);
        minSumDiscount.setAutomatic(false);
        minSumDiscount.setCombinable(true);
        minSumDiscount.setApplyForAllDiscountCategories(true);
        minSumDiscount.setDetailWhenPrinting(false);
        minSumDiscount.setPriority(0);
        minSumDiscount.setComment("");
        minSumDiscount.setDiscountApplicationMethod(DiscountApplicationMethod.WITH_OTHERS);
        minSumDiscount.setDiscountMode(DiscountMode.DISCOUNT);
        minSumDiscount.setDiscountAssignMode(DiscountAssignMode.FIRST_DISH);
        minSumDiscount.setDiscountCalculationMode(DiscountCalculationMode.PERCENT);
        minSumDiscount.setValidities(null);
        minSumDiscount.setDiscountCategories(null);
        discountService.save(minSumDiscount);

        Discount singleton = new Discount("Применяется при отсутствии других скидок");
        singleton.setEnabled(true);
        singleton.setValue(20);
        singleton.setType("Скидки и надбавки");
        singleton.setPaymentMethods((List<PaymentMethod>) paymentMethodService.getAll());
        singleton.setMinSum(0d);
        singleton.setMinSumRestriction(false);
        singleton.setScheduleRestriction(false);
        singleton.setManualSelectWithOthers(false);
        singleton.setManualInput(false);
        singleton.setManualDishSelect(false);
        singleton.setAutomatic(false);
        singleton.setCombinable(false);
        singleton.setApplyForAllDiscountCategories(true);
        singleton.setDetailWhenPrinting(false);
        singleton.setPriority(0);
        singleton.setComment("");
        singleton.setDiscountApplicationMethod(DiscountApplicationMethod.FULL_PRICE);
        singleton.setDiscountMode(DiscountMode.DISCOUNT);
        singleton.setDiscountAssignMode(DiscountAssignMode.FIRST_DISH);
        singleton.setDiscountCalculationMode(DiscountCalculationMode.PERCENT);
        singleton.setValidities(null);
        singleton.setDiscountCategories(null);
        discountService.save(singleton);

        Discount tuesdayDiscount = new Discount("Скидка по вторникам, с 1-00 до 23-00");
        tuesdayDiscount.setEnabled(true);
        tuesdayDiscount.setValue(20);
        tuesdayDiscount.setType("Скидки и надбавки");
        tuesdayDiscount.setPaymentMethods((List<PaymentMethod>) paymentMethodService.getAll());
        tuesdayDiscount.setMinSum(0d);
        tuesdayDiscount.setMinSumRestriction(false);
        tuesdayDiscount.setScheduleRestriction(true);
        tuesdayDiscount.setManualSelectWithOthers(false);
        tuesdayDiscount.setManualInput(false);
        tuesdayDiscount.setManualDishSelect(false);
        tuesdayDiscount.setAutomatic(false);
        tuesdayDiscount.setCombinable(true);
        tuesdayDiscount.setApplyForAllDiscountCategories(true);
        tuesdayDiscount.setDetailWhenPrinting(false);
        tuesdayDiscount.setPriority(0);
        tuesdayDiscount.setComment("");
        tuesdayDiscount.setDiscountApplicationMethod(DiscountApplicationMethod.FULL_PRICE);
        tuesdayDiscount.setDiscountMode(DiscountMode.DISCOUNT);
        tuesdayDiscount.setDiscountAssignMode(DiscountAssignMode.FIRST_DISH);
        tuesdayDiscount.setDiscountCalculationMode(DiscountCalculationMode.PERCENT);
        List<Validity> validitiesForDiscount = new ArrayList<>();
        validitiesForDiscount.add(validityService.findByNameValidity("Вторник, с 1-00 до 23-00"));

        tuesdayDiscount.setValidities(validitiesForDiscount);
        tuesdayDiscount.setDiscountCategories(null);
        discountService.save(tuesdayDiscount);
    }

    private void generateUnitsOfMeasurement() {
        UnitsOfMeasurement kg = new UnitsOfMeasurement("Киллограммы", "кг", true, 4747);
        UnitsOfMeasurement l = new UnitsOfMeasurement("Литры", "л", true, 4748);
        UnitsOfMeasurement portion = new UnitsOfMeasurement("Порция", "порц", true, 4749);
        unitsOfMeasurementService.save(kg);
        unitsOfMeasurementService.save(l);
        unitsOfMeasurementService.save(portion);
    }

}
