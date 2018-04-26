package com.pizza.crm.controller.admin;

import com.pizza.crm.service.ScaleOfSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScaleOfSizeController {

    private final ScaleOfSizeService scaleOfSizeService;

    @Autowired
    public ScaleOfSizeController(ScaleOfSizeService scaleOfSizeService) {
        this.scaleOfSizeService = scaleOfSizeService;
    }

    @RequestMapping("/scaleofsize")
    public String getAllScale(Model model) {
        model.addAttribute("scale", scaleOfSizeService.getAll());
        return "scaleOfSize";
    }
}
