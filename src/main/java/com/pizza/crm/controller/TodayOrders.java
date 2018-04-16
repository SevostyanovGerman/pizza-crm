package com.pizza.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodayOrders {

    @GetMapping("/todayOrders")
    public String todayOrders() {
        return "todayOrders";
    }
}
