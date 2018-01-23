package com.java_mentor.pizzacrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/"})
    public String indexPage() {
        return "index";
    }

}
