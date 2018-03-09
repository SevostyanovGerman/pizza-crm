package com.pizza.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/"})
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/adv")
    public String advPage() {
        return "advancedOptions";
    }

}
