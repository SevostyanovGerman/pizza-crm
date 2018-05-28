package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.model.Order;
import com.pizza.crm.model.Validity;
import com.pizza.crm.model.ValiditySchedule;
import com.pizza.crm.model.discount.Discount;
import com.pizza.crm.model.discount.DiscountApplicationMethod;
import com.pizza.crm.model.discount.DiscountMode;
import com.pizza.crm.service.DiscountService;
import com.pizza.crm.service.DishService;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
public class OrderControllerRest {

    private final DishService dishService;
    private final DiscountService discountService;

    public OrderControllerRest(DishService dishService, DiscountService discountService) {
        this.dishService = dishService;
        this.discountService = discountService;
    }

    @PostMapping("/admin/discount/getRowTotal")
    public List<Double> getRowTotal(@RequestBody Order order){

        Double rawTotal = 0d;
        Double total = 0d;
        List<Double> rawTotalAndTotal = new ArrayList<>();
        Double discountSum = 0d;
        Double extraChargeSum = 0d;
        LocalTime localTimeNow = LocalTime.now();
        DayOfWeek dayOfWeekNow = LocalDate.now().getDayOfWeek();



        //rowTotal cost calculation
        for (int i = 0; i < order.getDishes().size(); i++) {
            rawTotal += order.getDishes().get(i).getAmount() *
                    dishService.getDishByName(order.getDishes().get(i).getName()).getPrice();
        }
        total = rawTotal;

        //Adding discounts to the order, checking for no identical discounts
        List<Discount> discounts = new ArrayList<>(order.getDiscounts());
        order.getDiscounts().clear();
        for (Discount dis : discounts) {

            Discount discount = discountService.findByName(dis.getName());
            if (!(order.getDiscounts().contains(discount))) {

                // Checking discount data
                if (discount.isScheduleRestriction()) {
                    int i = 0;
                    for (Validity validity: discount.getValidities()) {
                        for (ValiditySchedule validitySchedule: validity.getValidityScheduleList()) {
                            if (localTimeNow.isAfter(validitySchedule.getBeginTime())&&
                                    localTimeNow.isBefore(validitySchedule.getEndTime())) {

                                //Checking the days of the week
                                List<DayOfWeek> dayOfWeeks = new ArrayList<>();
                                if (validitySchedule.getMonday()) {
                                    dayOfWeeks.add(DayOfWeek.MONDAY);
                                }
                                if (validitySchedule.getTuesday()) {
                                    dayOfWeeks.add(DayOfWeek.TUESDAY);
                                }
                                if (validitySchedule.getWednesday()) {
                                    dayOfWeeks.add(DayOfWeek.WEDNESDAY);
                                }
                                if (validitySchedule.getTuesday()) {
                                    dayOfWeeks.add(DayOfWeek.THURSDAY);
                                }
                                if (validitySchedule.getFriday()) {
                                    dayOfWeeks.add(DayOfWeek.FRIDAY);
                                }
                                if (validitySchedule.getSaturday()) {
                                    dayOfWeeks.add(DayOfWeek.SATURDAY);
                                }
                                if (validitySchedule.getSunday()) {
                                    dayOfWeeks.add(DayOfWeek.SUNDAY);
                                }

                                if (dayOfWeeks.contains(dayOfWeekNow)) {
                                    order.getDiscounts().add(discount);
                                    i++;
                                    break;
                                }

                            }
                        }
                        if (i > 0) {break;}
                    }
                } else {
                    order.getDiscounts().add(discount);
                }

            }
        }

        // Combinable discount
        for (int i = 0; i < order.getDiscounts().size(); i++) {
            if (!(order.getDiscounts().get(i).getCombinable()) && order.getDiscounts().size() > 1) {
                order.getDiscounts().remove(order.getDiscounts().get(i));
            }
        }

        // Minimum sum discount
        for (int i = 0; i < order.getDiscounts().size(); i++) {
            if (!(order.getDiscounts().get(i).isMinSumRestriction()) &&
                    order.getDiscounts().get(i).getMinSum() >= rawTotal) {
                order.getDiscounts().remove(order.getDiscounts().get(i));
            }
        }


        //Order cost calculation with discounts
            if (order.getDiscounts() != null) {
                for (Discount discount: order.getDiscounts()) {


                    if (discount.getDiscountApplicationMethod() == DiscountApplicationMethod.FULL_PRICE) {
                        if (discount.getDiscountMode() == DiscountMode.DISCOUNT) {
                            total = total - (rawTotal * discount.getValue()) / 100;
                            discountSum += discount.getValue();
                        } else if (discount.getDiscountMode() == DiscountMode.EXTRA_PAY) {
                            total = total + (rawTotal * discount.getValue()) / 100;
                            extraChargeSum += discount.getValue();
                        }
                    } else if (discount.getDiscountApplicationMethod() == DiscountApplicationMethod.WITH_OTHERS) {
                        if (discount.getDiscountMode() == DiscountMode.DISCOUNT) {
                            total = total - (total * discount.getValue()) / 100;
                            discountSum += discount.getValue();
                        } else if (discount.getDiscountMode() == DiscountMode.EXTRA_PAY) {
                            total = total + (total * discount.getValue()) / 100;
                            extraChargeSum += discount.getValue();
                        }
                    }

                }

            }

        
        rawTotalAndTotal.add(rawTotal);
        rawTotalAndTotal.add(total);
        rawTotalAndTotal.add(discountSum);
        rawTotalAndTotal.add(extraChargeSum);

        return rawTotalAndTotal;
    }

    @PostMapping("/admin/discount/getAllDiscountsForOrder")
    public Collection<Discount> getAllDiscountsForOrder(){
        return discountService.getEnabledDiscounts();
    }
}