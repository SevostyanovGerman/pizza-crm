package com.pizza.crm.controller.admin;

import com.pizza.crm.model.Schedule;
import com.pizza.crm.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DecreeController {

    private ScheduleService scheduleService;

    public DecreeController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestMapping("/newDecree")
    public String newDecree(Model model){
        model.addAttribute("schedules", scheduleService.findAllSchedules());
        return "admin/newDecree";
    }

    @PostMapping("/newDecree/save")
    public String save(@RequestBody Schedule schedule){
        scheduleService.save(schedule);
        return "redirect:/newDecree";
    }

}
