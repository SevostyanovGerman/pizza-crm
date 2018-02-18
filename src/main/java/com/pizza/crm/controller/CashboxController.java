package com.pizza.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CashboxController {

    @GetMapping("/cashbox")
    public String cahsbox() {
        return "/cashbox";
    }

}
