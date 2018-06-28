package com.pizza.crm.controller.admin;

import com.pizza.crm.model.SchemeModifiers;
import com.pizza.crm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SchemeModifiersController {
    private final NomenclatureService nomenclatureService;
    private final SchemeModifiersService schemeModifiersService;
    private final ScaleOfSizeService scaleOfSizeService;

    @Autowired
    public SchemeModifiersController(NomenclatureService nomenclatureService,
                                     SchemeModifiersService schemeModifiersService,
                                     ScaleOfSizeService scaleOfSizeService) {
        this.scaleOfSizeService = scaleOfSizeService;
        this.nomenclatureService = nomenclatureService;
        this.schemeModifiersService = schemeModifiersService;
    }

    @GetMapping("schemeModifiers")
    String schemeModifiers(Model model) {
        model.addAttribute("modifierNomenclatures", nomenclatureService.getNomenclatureModifiers());
        model.addAttribute("scale", scaleOfSizeService.getAll());
        return "admin/schemeModifiers";
    }

    @PostMapping("schemeModifiers/save")
    String save(@RequestBody SchemeModifiers schemeModifiers) {
        schemeModifiersService.save(schemeModifiers);
        return "redirect:/nomenclature";
    }
}
