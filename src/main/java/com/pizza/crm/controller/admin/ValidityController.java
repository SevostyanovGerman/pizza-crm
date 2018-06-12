package com.pizza.crm.controller.admin;

import com.pizza.crm.model.Validity;
import com.pizza.crm.model.ValiditySchedule;
import com.pizza.crm.repository.ValidityRepository;
import com.pizza.crm.service.ValidityScheduleService;
import com.pizza.crm.service.ValidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ValidityController {

    private final ValidityScheduleService validityScheduleService;
    private final ValidityService validityService;

    @Autowired
    public ValidityController(ValidityScheduleService validityScheduleService, ValidityService validityService) {
        this.validityScheduleService = validityScheduleService;
        this.validityService = validityService;
    }

    @RequestMapping("/validity")
    public String validity(Model model) {
        model.addAttribute("validity", validityService.getAll());
        model.addAttribute("schedule", validityScheduleService.findAllSchedules());
        return "admin/validity";
    }

    //Добавление названия расписания и одного графика
    @PostMapping("/schedule/addAll")
    public String addAll(@RequestBody Validity validity) {
        validityService.save(validity);
        return "redirect:/validity";
    }

    //Отдельное добавление графика
    @PostMapping("/schedule/addSchedule")
    public String addSchedule(@RequestParam String validityName,
                              @RequestParam LocalTime beginTime,
                              @RequestParam LocalTime endTime) {
        ValiditySchedule validitySchedule = new ValiditySchedule(beginTime, endTime);
        Validity validity = validityService.findByNameValidity(validityName);
        validity.getValidityScheduleList().add(validitySchedule);
        validityService.save(validity);
        return "redirect:/validity";
    }

    @PostMapping("/validity/deleteValidity")
    public String delete(@RequestParam String name) {
        validityService.deleteByNameValidity(name);
        return "redirect:/validity";
    }

    @PostMapping("/schedule/deleteSchedule")
    public String delete(@RequestParam Long id) {
        validityScheduleService.delete(id);
        return "redirect:/validity";
    }

    @PostMapping("/validity/addField")
    public String addField(@RequestBody Validity validity) {
        validityService.save(validity);
        return "redirect:/validity";
    }

    @PostMapping("/validity/save")
    public String save(@RequestBody Validity validity) {
        validityService.save(validity);
        return "redirect:/validity";
    }
}
