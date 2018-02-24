package com.pizza.crm.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DecreeController {

    @RequestMapping("/newDecree")
    public String newDecree(){
        return "admin/newDecree";
    }

}
