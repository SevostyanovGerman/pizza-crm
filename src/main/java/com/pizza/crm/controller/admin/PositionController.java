package com.pizza.crm.controller.admin;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.Position;
import com.pizza.crm.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PositionController {

    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/admin/staff/position")
    public String getAllPositions(Model model) {
        model.addAttribute("allPositions", positionService.getAll());
        return "admin/staff/position";
    }

    @GetMapping("/admin/staff/position/{id}")
    @ResponseBody
    public Position getPosition(@PathVariable Long id) {
        return positionService.findById(id).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping("/admin/staff/position/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePosition(@PathVariable Long id) {
        positionService.deleteById(id);
    }

    @PostMapping("/admin/staff/position")
    @ResponseStatus(HttpStatus.OK)
    public void savePosition(Position position) {
        positionService.save(position);
    }
}
