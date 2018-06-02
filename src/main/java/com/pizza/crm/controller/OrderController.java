package com.pizza.crm.controller;

import com.pizza.crm.model.Employee;
import com.pizza.crm.model.Order;
import com.pizza.crm.model.discount.Discount;
import com.pizza.crm.model.security.User;
import com.pizza.crm.service.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    private final EmployeeService employeeService;
    private final OrderService orderService;
    private final NomenclatureParentGroupService nomenclatureParentGroupService;

    @Autowired
    public OrderController(EmployeeService employeeService,OrderService orderService,
                           NomenclatureParentGroupService nomenclatureParentGroupService) {
        this.nomenclatureParentGroupService=nomenclatureParentGroupService;
        this.employeeService = employeeService;
        this.orderService = orderService;
    }

    @RequestMapping("/order")
    public String orderPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeService.findByPincode(user.getPincode()).orElseGet(Employee::new);
        String employeeLogin = employee.getLogin();

        model.addAttribute("nomenclatureParentGroups",nomenclatureParentGroupService.findAlNomenclatureParentGroups());
        model.addAttribute("employeeLogin", employeeLogin);

        Order order = new Order();
        orderService.save(order);
        model.addAttribute("newOrder", order);

        return "order";
    }

}
