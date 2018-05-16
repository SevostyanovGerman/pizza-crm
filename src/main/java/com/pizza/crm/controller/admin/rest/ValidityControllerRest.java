package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.model.Validity;
import com.pizza.crm.model.ValiditySchedule;
import com.pizza.crm.service.ValidityScheduleService;
import com.pizza.crm.service.ValidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ValidityControllerRest {

    private final ValidityScheduleService validityScheduleService;
    private final ValidityService validityService;

    @Autowired
    public ValidityControllerRest(ValidityScheduleService validityScheduleService, ValidityService validityService) {
        this.validityScheduleService = validityScheduleService;
        this.validityService = validityService;
    }

    /*@RequestMapping(value = "/validity/get", method = RequestMethod.POST)
    public ValiditySchedule getSchedule(String name) {
        return validityScheduleService.getScheduleByName(name);
    }*/

    @RequestMapping(value = "/validity/get", method = RequestMethod.POST)
    public List<ValiditySchedule> getSchedule(@RequestParam String nameValidity) {
        Validity validity = validityService.findByNameValidity(nameValidity);
        return validity.getValidityScheduleList();
    }

}
