package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.Schedule;
import com.pizza.crm.model.discount.Discount;
import com.pizza.crm.service.DiscountService;
import com.pizza.crm.service.ScheduleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DiscountControllerRest {

    private final ScheduleService scheduleService;
    private final DiscountService discountService;

    public DiscountControllerRest(ScheduleService scheduleService, DiscountService discountService) {
        this.scheduleService = scheduleService;
        this.discountService = discountService;
    }

    @PostMapping("/admin/discount/getSchedule")
    public Schedule getSchedule(@RequestBody Schedule schedule){
        return scheduleService.getSchedule(schedule.getId());
    }

    @PostMapping("/admin/discount/getAllDiscounts")
    public Collection<Discount> getAllDiscounts(){
        return discountService.getAll();
    }

    @PostMapping("/admin/discount/getAutoDiscountsValue")
    public Integer getAutoDiscountsValue(){
        Integer autoDiscountsValue = 0;
        for (Discount discount: discountService.getAll()) {
            if (discount.getAutomatic()) {
                autoDiscountsValue += discount.getValue();
            }
        }
        return autoDiscountsValue;
    }
}
