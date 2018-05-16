package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.model.Schedule;
import com.pizza.crm.service.ScheduleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User on 12.05.2018.
 */
@RestController
public class DiscountControllerRest {

    private final ScheduleService scheduleService;

    public DiscountControllerRest(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/admin/discount/getSchedule")
    public Schedule getSchedule(@RequestBody Schedule schedule){
        return scheduleService.getSchedule(schedule.getId());
    }
}
