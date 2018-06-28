package com.pizza.crm.controller;

import com.pizza.crm.service.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductSearchController {

    private final NomenclatureService nomenclatureService;

    @Autowired
    public ProductSearchController(NomenclatureService nomenclatureService) {
        this.nomenclatureService = nomenclatureService;
    }

    @GetMapping("/productSearch")
    public String productSearchPage(Model model) {
        model.addAttribute("products", nomenclatureService.findAllNomenclatures());
        return "/productSearch";
    }

}
