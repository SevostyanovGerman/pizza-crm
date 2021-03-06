package com.pizza.crm.controller.admin;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.Employee;
import com.pizza.crm.model.security.Role;
import com.pizza.crm.model.security.User;
import com.pizza.crm.service.DepartmentService;
import com.pizza.crm.service.EmployeeService;
import com.pizza.crm.service.PositionService;
import com.pizza.crm.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PositionService positionService;
    private final DepartmentService departmentService;
    private final RoleService roleService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, PositionService positionService,
                              DepartmentService departmentService, RoleService roleService
    ) {
        this.employeeService = employeeService;
        this.positionService = positionService;
        this.departmentService = departmentService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/staff/employee")
    public String getAllEmployees(Model model) {
        model.addAttribute("allEmployees", employeeService.getAll());
        return "admin/staff/employee";
    }

    @GetMapping("/admin/staff/employee/new")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee("Новый сотрудник", "password", "1234"));
        return "admin/staff/employeeCard";
    }

    @GetMapping("/admin/staff/employee/{id}")
    public String getEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("employee", employee);
        model.addAttribute("allPositions", positionService.getAll());
        model.addAttribute("allDepartments", departmentService.getAll());
        return "admin/staff/employeeCard";
    }

    @PostMapping("/admin/staff/employee")
    public String saveEmployee(@Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/staff/employeeCard";
        }
        Role role = roleService.getByName("USER");
        User user = new User(employee.getPincode(), true, Collections.singletonList(role));
        role.setUsers(Collections.singletonList(user));
        employee.setUser(user);
        employeeService.save(employee);
        return "redirect:/admin/staff/employee";
    }

    @DeleteMapping("/admin/staff/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
    }

    @GetMapping("/admin/staff/employee/find")
    @ResponseBody
    public Employee findEmployeeByPincode(@RequestParam("pincode") String pincode) {
        return employeeService.findByPincode(pincode).orElseGet(Employee::new);
    }

}
