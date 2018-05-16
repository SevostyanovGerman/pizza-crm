package com.pizza.crm.controller.admin;

import com.pizza.crm.model.ValiditySchedule;
import com.pizza.crm.service.ValidityScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DecreeController {

    private final ValidityScheduleService validityScheduleService;

    @Autowired
    public DecreeController(ValidityScheduleService validityScheduleService) {
        this.validityScheduleService = validityScheduleService;
    }

    @RequestMapping("/newDecree")
    public String newDecree(Model model) {
        model.addAttribute("schedules", validityScheduleService.findAllSchedules());
        return "admin/newDecree";
    }

    @PostMapping("/newDecree/save")
    public String save(@RequestBody ValiditySchedule validitySchedule) {
        validityScheduleService.save(validitySchedule);
        return "redirect:/newDecree";
    }

    @RequestMapping("/getDecree")
    public String getDecree(Model model) {
        model.addAttribute("schedules", validityScheduleService.findAllSchedules());
        return "admin/decree";
    }


}
