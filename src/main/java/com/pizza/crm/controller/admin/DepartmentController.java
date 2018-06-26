package com.pizza.crm.controller.admin;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.Department;
import com.pizza.crm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/admin/staff/department")
    public String getAllDepartments(Model model) {
        model.addAttribute("allDepartments", departmentService.getAll());
        return "/admin/staff/department";
    }

    @GetMapping("/admin/staff/department/{id}")
    @ResponseBody
    public Department getAllDepartments(@PathVariable Long id) {
        return departmentService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/admin/staff/department")
    public ResponseEntity<?> saveDepartment(@Validated Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(bindingResult.getFieldError());
        }
        departmentService.save(department);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin/staff/department/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
