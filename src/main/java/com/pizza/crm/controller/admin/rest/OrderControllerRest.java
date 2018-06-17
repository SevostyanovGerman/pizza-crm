package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.*;
import com.pizza.crm.model.discount.Discount;
import com.pizza.crm.model.discount.DiscountApplicationMethod;
import com.pizza.crm.model.discount.DiscountMode;
import com.pizza.crm.service.DiscountService;
import com.pizza.crm.service.DishService;
import com.pizza.crm.service.NomenclatureService;
import com.pizza.crm.service.OrderService;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class OrderControllerRest {

    private final DishService dishService;
    private final DiscountService discountService;
    private final OrderService orderService;
    private final NomenclatureService nomenclatureService;

    public OrderControllerRest(DishService dishService, DiscountService discountService, OrderService orderService,
                                    NomenclatureService nomenclatureService) {
        this.nomenclatureService = nomenclatureService;
        this.dishService = dishService;
        this.discountService = discountService;
        this.orderService = orderService;
    }

    @PostMapping("/discount/getRowTotal")
    public List<Double> getRowTotal(@RequestBody Order order){

        Double rawTotal = 0d;
        Double total = 0d;
        List<Double> rawTotalAndTotal = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        List<String> dishNames = new ArrayList<>();

        //rowTotal cost calculation
        for (Dish dish: order.getDishes()) {
            dishNames.add(dish.getName());
            rawTotal += dish.getAmount() * nomenclatureService.getNomenclatureByName(dish.getName()).getPrice();
        }
        total = rawTotal;

        



        List<String> nameDiscounts = new ArrayList<>();
        for (Discount d : order.getDiscounts()) {
            nameDiscounts.add(d.getName());
        }
        List<Discount> list = new ArrayList<>();
        list = discountService.getDiscountsForOrder(nameDiscounts, dayOfWeekNow);
        order.setDiscounts(list);

//            final Double rawTotalStream = rawTotal;
//            discountStream = order.getDiscounts().stream()
//                .map(discount -> discount = discountService.findByName(discount.getName()))
//                .distinct()
//                .filter(discount -> discount.getValidities().stream()
//                        .noneMatch(validity -> validity.getValidityScheduleList().stream()
//                                .noneMatch(validitySchedule ->
//                                        (validitySchedule.getDayOfWeekList().contains(dayOfWeekNow))
//                                )
//                        )
//                )
//                    .filter(discount -> discount.getValidities().stream()
//                            .noneMatch(validity -> validity.getValidityScheduleList().stream()
//                                    .noneMatch(validitySchedule ->
//                                            (localTimeNow.isAfter(validitySchedule.getBeginTime())&&
//                                                    localTimeNow.isBefore(validitySchedule.getEndTime()))
//                                    )
//                            )
//                    )
//                    .filter(discount -> !(discount.isMinSumRestriction() &&
//                            discount.getMinSum().compareTo(rawTotalStream) > 0)
//                    )
//                    .filter(discount -> !(!(discount.getCombinable()) && order.getDiscounts().size() > 1)
//                    )
//                    .collect(Collectors.toList());
//            order.setDiscounts(discountStream);
        
        //Order cost calculation with discounts
        if (order.getDiscounts() != null) {
            for (Discount discount: order.getDiscounts()) {
                switch (discount.getDiscountApplicationMethod()) {
                    case FULL_PRICE:
                        if (discount.getDiscountMode() == DiscountMode.DISCOUNT) {
                            total = total - (rawTotal * discount.getValue()) / 100;
                            discountSum += discount.getValue();
                        } else if (discount.getDiscountMode() == DiscountMode.EXTRA_PAY) {
                            total = total + (rawTotal * discount.getValue()) / 100;
                            extraChargeSum += discount.getValue();
                        }
                        break;
                    case WITH_OTHERS:
                        if (discount.getDiscountMode() == DiscountMode.DISCOUNT) {
                            total = total - (total * discount.getValue()) / 100;
                            discountSum += discount.getValue();
                        } else if (discount.getDiscountMode() == DiscountMode.EXTRA_PAY) {
                            total = total + (total * discount.getValue()) / 100;
                            extraChargeSum += discount.getValue();
                        }
                        break;
                }
            }
        }

        order.setPrice(rawTotal);
        order.setDiscountedPrice(total);
        order.setCreationDate(localDateTime);
        order.setDishes(dishService.getDishesByName(dishNames));
        orderService.save(order);

        rawTotalAndTotal.add(order.getPrice());
        rawTotalAndTotal.add(order.getDiscountedPrice());

        return rawTotalAndTotal;
    }

    @PostMapping("/discount/getAllDiscountsForOrder")
    public Collection<Discount> getAllDiscountsForOrder(){
        return discountService.getEnabledDiscounts();
    }
}