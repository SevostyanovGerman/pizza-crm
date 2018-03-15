package com.pizza.crm.controller.admin;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.Employee;
import com.pizza.crm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/back-office/staff")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public String getAllEmployees(Model model) {
        model.addAttribute("allEmployees", employeeService.getAll());
        return "/admin/staff/employee";
    }

    @GetMapping("/employee/{id}")
    public @ResponseBody Employee getEmployee(@PathVariable Long id) {
        return employeeService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public void saveEmployee(Employee employee) {
        employeeService.save(employee);
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
    }


}
