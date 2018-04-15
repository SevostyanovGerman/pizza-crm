package com.pizza.crm.controller.admin;

import com.pizza.crm.model.UnitsOfMeasurement;
import com.pizza.crm.service.UnitsOfMeasurmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
public class UnitsOfMeasurementController {

    private final UnitsOfMeasurmentService unitsOfMeasurment;

    @Autowired
    public UnitsOfMeasurementController(UnitsOfMeasurmentService unitsOfMeasurment) {
        this.unitsOfMeasurment = unitsOfMeasurment;
    }

    @RequestMapping("/measurement")
    public String getAllUnits(Model model) {
        model.addAttribute("allMeasurement", unitsOfMeasurment.getAll());
        model.addAttribute("AddUnit", new UnitsOfMeasurement());
        model.addAttribute("UpdateUnit", new UnitsOfMeasurement());
        return "UnitsOfMeasurement";
    }

    @RequestMapping(value = "/measurement/add")
    public String addUnit(@ModelAttribute("AddUnit") UnitsOfMeasurement unit) {
        unitsOfMeasurment.save(unit);
        return "redirect:/measurement";
    }


    @RequestMapping(value = "/measurement/delete/{id}")
    public String deleteUnit(@PathVariable("id") Long id) {
        unitsOfMeasurment.deleteById(id);
        return "redirect:/measurement";
    }

    @RequestMapping(value = "/measurement/update")
    public String updateUser(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("shortName") String shortName,
            @RequestParam(value = "basic", defaultValue = "false") boolean basic,
            @RequestParam("code") int code){

        UnitsOfMeasurement unit = new UnitsOfMeasurement(id, name, shortName, basic, code);
        unitsOfMeasurment.save(unit);
        return "redirect:/measurement";
    }



}
