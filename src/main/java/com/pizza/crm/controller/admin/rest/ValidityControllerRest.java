package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.model.Schedule;
import com.pizza.crm.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidityControllerRest {

    private final ScheduleService scheduleService;

    @Autowired
    public ValidityControllerRest(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestMapping(value = "/validity/get", method = RequestMethod.POST)
    public Schedule getSchedule(String name) {
        return scheduleService.getScheduleByName(name);
    }

}
