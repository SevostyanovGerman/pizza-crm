package com.pizza.crm.init;

import com.github.javafaker.Faker;
import com.pizza.crm.model.*;
import com.pizza.crm.model.discount.*;
import com.pizza.crm.config.security.Role;
import com.pizza.crm.config.security.User;
import com.pizza.crm.service.*;
import com.pizza.crm.service.RoleService;
import com.pizza.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.pizza.crm.model.AccountingCategory.PRODUCT;

//TODO почистить от лишнего
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
    private final NomenclatureQuickMenuService nomenclatureQuickMenuService;
    private final EmployeeService employeeService;
    private final PaymentMethodService paymentMethodService;
    private final PaymentTypeService paymentTypeService;
    private final UnitsOfMeasurementService unitsOfMeasurementService;
    private final ScaleOfSizeService scaleOfSizeService;

    @Autowired
    public DbDataGenerator(NomenclatureParentGroupService nomenclatureParentGroupService,
                           NomenclatureService nomenclatureService, UserService userService, RoleService roleService,
                           AddedCategoryService addedCategoryService, CategoryService categoryService,
                           DishService dishService, IngredientService ingredientService,
                           ValidityScheduleService validityScheduleService, ValidityService validityService,
                           DiscountService discountService, DecreeService decreeService,
                           QuickMenuService quickMenuService, NomenclatureQuickMenuService nomenclatureQuickMenuService,
                           EmployeeService employeeService, PaymentMethodService paymentMethodService,
                           PaymentTypeService paymentTypeService, UnitsOfMeasurementService unitsOfMeasurementService,
                           ScaleOfSizeService scaleOfSizeService) {
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
        this.nomenclatureQuickMenuService = nomenclatureQuickMenuService;
        this.employeeService = employeeService;
        this.paymentMethodService = paymentMethodService;
        this.paymentTypeService = paymentTypeService;
        this.unitsOfMeasurementService = unitsOfMeasurementService;
        this.scaleOfSizeService = scaleOfSizeService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        generateUsers();
        generateNomenclatureAndNomenclatureParentGroup();
        generateDishes();
        generateValidity();
        generateFakeStaff();
        generateDiscountsAndPaymentMethods();
        generateAddedCategory();
        generateDecree();

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
            Role role = roleService.getByName("USER");
            User user = new User(employee.getPincode(), true);
            user.setRoles(Collections.singletonList(role));
            employee.setUser(user);
            return employee;
        })
                .limit(50)
                .collect(Collectors.toList());

        Random random = new Random();

        fakeEmployees.forEach((e) -> {
            Department department = fakeDepartments.get(random.nextInt(fakeDepartments.size()));
            e.addDepartment(department);
            Position position = fakePositions.get(random.nextInt(fakePositions.size()));
            e.addPosition(position);
        });
        employeeService.saveAll(fakeEmployees);
    }

    private void generateDishes() {
        Faker faker = new Faker(new Locale("ru"));

        Set<Nomenclature> nomenclatures1 = nomenclatureService
                .findAllNomenclatures()
                .stream()
                .filter(nomenclature -> nomenclature.getNomenclatureType().equals(NomenclatureType.DISH))
                .limit(3)
                .collect(Collectors.toSet());

        Set<Nomenclature> nomenclatures2 = nomenclatureService
                .findAllNomenclatures()
                .stream()
                .filter(nomenclature -> nomenclature.getNomenclatureType().equals(NomenclatureType.DISH))
                .skip(10)
                .limit(5)
                .collect(Collectors.toSet());
        Set<Nomenclature> nomenclatures3 = nomenclatureService
                .findAllNomenclatures()
                .stream()
                .filter(nomenclature -> nomenclature.getNomenclatureType().equals(NomenclatureType.DISH))
                .skip(20)
                .limit(4)
                .collect(Collectors.toSet());

        NomenclatureQuickMenu nomenclatureQuickMenu1 = new NomenclatureQuickMenu(faker.color().name(), 1, nomenclatures1);
        NomenclatureQuickMenu nomenclatureQuickMenu2 = new NomenclatureQuickMenu(faker.color().name(), 1, nomenclatures2);
        NomenclatureQuickMenu nomenclatureQuickMenu3 = new NomenclatureQuickMenu(faker.color().name(), 2, nomenclatures3);

        Set<NomenclatureQuickMenu> nomenclatureQuickMenus1 = new HashSet<>();
        Set<NomenclatureQuickMenu> nomenclatureQuickMenus2 = new HashSet<>();
        nomenclatureQuickMenus1.add(nomenclatureQuickMenu1);
        nomenclatureQuickMenus2.add(nomenclatureQuickMenu2);
        nomenclatureQuickMenus1.add(nomenclatureQuickMenu3);
        nomenclatureQuickMenuService.save(nomenclatureQuickMenu1);
        nomenclatureQuickMenuService.save(nomenclatureQuickMenu2);
        nomenclatureQuickMenuService.save(nomenclatureQuickMenu3);

        quickMenuService.save(new QuickMenu("Горячее", nomenclatureQuickMenus1, DayOfWeek.MONDAY));
        quickMenuService.save(new QuickMenu("Салаты", nomenclatureQuickMenus2, DayOfWeek.MONDAY));
        quickMenuService.save(new QuickMenu("Напитки", nomenclatureQuickMenus1, DayOfWeek.MONDAY));

        quickMenuService.save(new QuickMenu("Горячее", nomenclatureQuickMenus1, DayOfWeek.TUESDAY));
        quickMenuService.save(new QuickMenu("Салаты", nomenclatureQuickMenus2, DayOfWeek.TUESDAY));
        quickMenuService.save(new QuickMenu("Напитки", nomenclatureQuickMenus1, DayOfWeek.TUESDAY));

        quickMenuService.save(new QuickMenu("Горячее", nomenclatureQuickMenus1, DayOfWeek.WEDNESDAY));
        quickMenuService.save(new QuickMenu("Салаты", nomenclatureQuickMenus2, DayOfWeek.WEDNESDAY));
        quickMenuService.save(new QuickMenu("Напитки", nomenclatureQuickMenus1, DayOfWeek.WEDNESDAY));

        quickMenuService.save(new QuickMenu("Горячее", nomenclatureQuickMenus1, DayOfWeek.THURSDAY));
        quickMenuService.save(new QuickMenu("Салаты", nomenclatureQuickMenus2, DayOfWeek.THURSDAY));
        quickMenuService.save(new QuickMenu("Напитки", nomenclatureQuickMenus1, DayOfWeek.THURSDAY));

        quickMenuService.save(new QuickMenu("Горячее", nomenclatureQuickMenus1, DayOfWeek.FRIDAY));
        quickMenuService.save(new QuickMenu("Салаты", nomenclatureQuickMenus2, DayOfWeek.FRIDAY));
        quickMenuService.save(new QuickMenu("Напитки", nomenclatureQuickMenus1, DayOfWeek.FRIDAY));

        quickMenuService.save(new QuickMenu("Горячее", nomenclatureQuickMenus1, DayOfWeek.SATURDAY));
        quickMenuService.save(new QuickMenu("Салаты", nomenclatureQuickMenus2, DayOfWeek.SATURDAY));
        quickMenuService.save(new QuickMenu("Напитки", nomenclatureQuickMenus1, DayOfWeek.SATURDAY));

        quickMenuService.save(new QuickMenu("Горячее", nomenclatureQuickMenus1, DayOfWeek.SUNDAY));
        quickMenuService.save(new QuickMenu("Салаты", nomenclatureQuickMenus2, DayOfWeek.SUNDAY));
        quickMenuService.save(new QuickMenu("Напитки", nomenclatureQuickMenus1, DayOfWeek.SUNDAY));
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

        for (int i = 0; i < 15; i++) {
            decreeService.save(new Decree("001", formatDateTime, formatDateTime, "123", "1111", false));
        }

    }

    private void generateNomenclatureAndNomenclatureParentGroup() {

        Random generator = new Random(100);
        Faker faker = new Faker(new Locale("ru"));
        List<Nomenclature> nomenclaturesList = new ArrayList<>();
        Set<NomenclatureParentGroup> parentSet = new HashSet<>();

        for (int j = 0; j < 25; j++) {
            nomenclaturesList.clear();
            parentSet.clear();
            NomenclatureParentGroup nomenclaturePG = new NomenclatureParentGroup(faker.food().ingredient());
            parentSet.add(nomenclaturePG);

            for (int i = 0; i < 25; i++) {

                LocalTime localTime1 = LocalTime.MIN.plusSeconds(generator.nextLong());
                LocalTime localTime2 = LocalTime.MIN.plusSeconds(generator.nextLong());

                Nomenclature nomenclature = new Nomenclature(
                        faker.number().randomDigit(),
                        faker.number().randomDouble(1, 10, 10000),
                        localTime1,
                        localTime2,
                        faker.food().ingredient(),
                        NomenclatureType.DISH,
                        PRODUCT,
                        faker.address().city());

                nomenclaturesList.add(nomenclature);
                nomenclatureService.save(nomenclature);
                nomenclature.setNomenclatureParentGroupSet(parentSet);
            }

            nomenclaturePG.setNomenclatures(nomenclaturesList);
            nomenclatureParentGroupService.save(nomenclaturePG);
        }
    }

    private void generateScaleOfSize() {
        Faker faker = new Faker(new Locale("ru"));
        List<ScaleOfSizeValues> valuesList = new ArrayList<>();


        for (int i = 0; i < 5; i++) {
            valuesList.clear();
            ScaleOfSize scale = new ScaleOfSize(faker.food().ingredient());
            scaleOfSizeService.save(scale);

            for (int j = 0; j < 10; j++) {
                ScaleOfSizeValues values = new ScaleOfSizeValues(faker.food().spice(), "0.3 л.", false);
                valuesList.add(values);
            }
            scale.setValuesList(valuesList);
            scaleOfSizeService.save(scale);
        }
    }

    private void generateIngredient() {
        Ingredient ingredient = new Ingredient("Dough", 1.0, "1", "kg");
        ingredientService.save(ingredient);
        //dishPizza.addIngredient(ingredient);
    }

    private void generateValidity() {
        Faker faker = new Faker(new Locale("ru"));
        List<ValiditySchedule> listValiditySchedule = new ArrayList<>();
        Set<String> validitySet = new HashSet<>();

        for (int i = 0; i < 15; i++) {
            listValiditySchedule.clear();


            List<Validity> validityList = (List<Validity>) validityService.getAll();

            if (validityList.size() == 0){
                String fakerName = faker.beer().name();
                validitySet.add(fakerName);
                List<String> list = new ArrayList<>(validitySet);
                Validity validity = new Validity();
                validity.setNameValidity(list.get(i));
                validityService.save(validity);

            } else {
                String fakerName = faker.beer().name();
                boolean flag = false;

                for (int k = 0; k < validityList.size(); k++) {
                    String validityName = validityList.get(k).getNameValidity();
                    if (fakerName.equals(validityName)){
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    Validity validity = new Validity();
                    validity.setNameValidity(fakerName);
                    validityService.save(validity);

                    for (int j = 0; j < 13; j++) {
                        ValiditySchedule schedule = new ValiditySchedule(LocalTime.of(18, 00), LocalTime.of(20, 00));
                        listValiditySchedule.add(schedule);
                        validity.setValidityScheduleList(listValiditySchedule);
                        validityService.save(validity);
                    }
                }
            }
        }
    }

    private void generateDiscountsAndPaymentMethods() {
        Faker faker = new Faker(new Locale("ru"));

        for (int i = 0; i < 3; i++) {
            Discount discount = new Discount(faker.commerce().productName());
            discountService.save(discount);
        }

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
        discount.isEnabled(true);
        discount.setValue(5d);
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
        extraCharge.isEnabled(true);
        extraCharge.setValue(10d);
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
        defaultDiscount.isEnabled(true);
        defaultDiscount.setValue(15d);
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
        minSumDiscount.isEnabled(true);
        minSumDiscount.setValue(30d);
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
        singleton.isEnabled(true);
        singleton.setValue(20d);
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
        tuesdayDiscount.isEnabled(true);
        tuesdayDiscount.setValue(20d);
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
        Faker faker = new Faker(new Locale("ru"));

        for (int i = 0; i < 15; i++) {
            String name = faker.food().measurement();
            UnitsOfMeasurement units = new UnitsOfMeasurement(name, name , true, 4747);
            unitsOfMeasurementService.save(units);
        }

    }

}
