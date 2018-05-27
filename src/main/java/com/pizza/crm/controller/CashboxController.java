package com.pizza.crm.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit;
import java.util.Map;

@Controller
@RequestMapping("/cashbox")
public class CashboxController {

    @RequestMapping(method = RequestMethod.GET)
    public String cahsbox(Model model) {
        System.out.println(model);
        return "/cashbox";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void getList(HttpServletRequest request) {
        System.out.println("Cashbox post method");
        for (Map.Entry<String, String[]> entry: request.getParameterMap().entrySet()) {
            System.out.println(entry.getKey());
        }
    }

}
