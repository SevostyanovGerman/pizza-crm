package com.pizza.crm.controller.admin;

import com.pizza.crm.model.UnitsOfMeasurement;
import com.pizza.crm.service.UnitsOfMeasurmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnitsOfMeasurementController {

    public final UnitsOfMeasurmentService unitsOfMeasurment;

    @Autowired
    public UnitsOfMeasurementController(UnitsOfMeasurmentService unitsOfMeasurment) {
        this.unitsOfMeasurment = unitsOfMeasurment;
    }

    @RequestMapping("/measurement")
    public String getAllMeasurement(Model model) {
        model.addAttribute("allMeasurement", unitsOfMeasurment.getAll());
        model.addAttribute("AddUnit", new UnitsOfMeasurement());
        return "UnitsOfMeasurement";
    }

    @RequestMapping(value = "/measurement/add")
    public String addUser(@ModelAttribute("AddUnit") UnitsOfMeasurement unit){
        unitsOfMeasurment.save(unit);
        return "redirect:/measurement";
    }


}
