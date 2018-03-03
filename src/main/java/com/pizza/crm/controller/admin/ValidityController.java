package com.pizza.crm.controller.admin;

import com.pizza.crm.model.Schedule;
import com.pizza.crm.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ValidityController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/validity")
    public String validity(Model model){
        model.addAttribute("schedules", scheduleService.findAllSchedules());
        return "admin/validity";
    }

    @PostMapping("/validity/save")
    public String save(@RequestBody Schedule schedule){
        scheduleService.save(schedule);
        return "redirect:/validity";
    }

    @PostMapping("/validity/delete")
    public String delete(@RequestParam String name){
        scheduleService.deleteByName(name);
        return "redirect:/validity";
    }
}
