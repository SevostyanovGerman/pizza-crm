package com.pizza.crm.controller;

import com.pizza.crm.model.Employee;
import com.pizza.crm.model.security.User;
import com.pizza.crm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodayOrdersController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/todayOrders")
    public String todayOrders(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeService.findByPincode(user.getPincode()).orElseGet(Employee::new);
        String employeeLogin = employee.getLogin();

        model.addAttribute("employeeLogin", employeeLogin);
        return "todayOrders";
    }
}
