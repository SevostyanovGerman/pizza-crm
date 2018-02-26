package com.pizza.crm.controller.admin;

import com.pizza.crm.model.Schedule;
import com.pizza.crm.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DecreeController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/newDecree")
    public String newDecree(){
        return "admin/newDecree";
    }

    @PostMapping("/newDecree/save")
    public String save(Schedule schedule){
        scheduleService.save(schedule);
        return "redirect:/newDecree";
    }

}
