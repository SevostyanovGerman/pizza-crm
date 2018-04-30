package com.pizza.crm.controller.admin;

import com.pizza.crm.model.ScaleOfSize;
import com.pizza.crm.service.ScaleOfSizeService;
import com.pizza.crm.service.ScaleOfSizeValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScaleOfSizeController {

    private final ScaleOfSizeService scaleOfSizeService;
    private final ScaleOfSizeValuesService scaleOfSizeValuesService;

    @Autowired
    public ScaleOfSizeController(ScaleOfSizeService scaleOfSizeService, ScaleOfSizeValuesService scaleOfSizeValuesService) {
        this.scaleOfSizeService = scaleOfSizeService;
        this.scaleOfSizeValuesService = scaleOfSizeValuesService;
    }

    @RequestMapping("/scale_of_size")
    public String getAllScale(Model model) {
        model.addAttribute("scale", scaleOfSizeService.getAll());
        model.addAttribute("units", scaleOfSizeValuesService.getAll());
        return "scale_of_size";
    }

    @PostMapping("/scale_of_size/save")
    public String save(@RequestBody ScaleOfSize scale) {
            scaleOfSizeService.save(scale);
        return "redirect:/scale_of_size";
    }

    @PostMapping("/scale_of_size/delete")
    public String delete(@RequestParam String name) {
        //scaleOfSizeService.deleteByName(name);
        return "redirect:/validity";
    }
}
