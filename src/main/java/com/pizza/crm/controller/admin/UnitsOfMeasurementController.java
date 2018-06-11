package com.pizza.crm.controller.admin;

import com.pizza.crm.model.UnitsOfMeasurement;
import com.pizza.crm.service.UnitsOfMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UnitsOfMeasurementController {

    private final UnitsOfMeasurementService unitsOfMeasurement;

    @Autowired
    public UnitsOfMeasurementController(UnitsOfMeasurementService unitsOfMeasurement) {
        this.unitsOfMeasurement = unitsOfMeasurement;
    }

    @RequestMapping("/measurement")
    public String getAllUnits(Model model) {
        model.addAttribute("allMeasurement", unitsOfMeasurement.getAll());
        model.addAttribute("AddUnit", new UnitsOfMeasurement());
        model.addAttribute("UpdateUnit", new UnitsOfMeasurement());
        return "admin/UnitsOfMeasurement";
    }

    @RequestMapping(value = "/measurement/add")
    public String addUnit(@ModelAttribute("AddUnit") UnitsOfMeasurement unit) {
        unitsOfMeasurement.save(unit);
        return "redirect:/measurement";
    }

    @RequestMapping(value = "/measurement/delete/{id}")
    public String deleteUnit(@PathVariable("id") Long id) {
        unitsOfMeasurement.deleteById(id);
        return "redirect:/measurement";
    }

    @RequestMapping(value = "/measurement/update")
    public String updateUser(@RequestBody UnitsOfMeasurement unit) {
        unitsOfMeasurement.save(unit);
        return "redirect:/measurement";
    }

    @PostMapping(value = "/measurement/changeBasic")
    public String changeBasic(@RequestParam String name, @RequestParam Boolean basic) {
        UnitsOfMeasurement measurement = unitsOfMeasurement.findByName(name);
        measurement.setBasic(basic);
        unitsOfMeasurement.save(measurement);
        return "redirect:/measurement";
    }
}
