package com.pizza.crm.controller.admin;

import com.pizza.crm.service.ValidityScheduleService;
import com.pizza.crm.service.ValidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DecreeController {

    private final ValidityService validityService;

    @Autowired
    public DecreeController(ValidityService validityService) {
        this.validityService = validityService;
    }

    @RequestMapping("/newDecree")
    public String newDecree(Model model) {
        model.addAttribute("validity", validityService.getAll());
        return "admin/newDecree";
    }

    @RequestMapping("/getDecree")
    public String getDecree() {
        return "admin/decree";
    }


}


