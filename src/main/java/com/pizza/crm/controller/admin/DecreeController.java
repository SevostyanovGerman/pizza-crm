package com.pizza.crm.controller.admin;

import com.pizza.crm.model.Schedule;
import com.pizza.crm.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DecreeController {

    @Autowired
    private ScheduleService scheduleService;

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
