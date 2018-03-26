package com.pizza.crm.controller.admin;

import com.pizza.crm.model.Schedule;
import com.pizza.crm.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
public class DecreeController {

    private final ScheduleService scheduleService;

    @Autowired
    public DecreeController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestMapping("/newDecree")
    public String newDecree(Model model) {
        model.addAttribute("schedules", scheduleService.findAllSchedules());
        return "admin/newDecree";
    }

    @PostMapping("/newDecree/save")
    public String save(@RequestBody Schedule schedule) {
        scheduleService.save(schedule);
        return "redirect:/newDecree";
    }

    @RequestMapping("/getDecree")
    public String getDecree(Model model) {
        model.addAttribute("schedules", scheduleService.findAllSchedules());
        return "admin/decree";
    }


}
