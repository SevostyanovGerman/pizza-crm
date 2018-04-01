package com.pizza.crm.controller.admin;

import com.pizza.crm.model.Nomenclature;
import com.pizza.crm.service.NomenclatureParentGroupService;
import com.pizza.crm.service.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NomenclatureController {

    private final NomenclatureService nomenclatureService;

    private final NomenclatureParentGroupService nomenclatureParentGroupService;

    @Autowired
    public NomenclatureController(NomenclatureService nomenclatureService,
                                  NomenclatureParentGroupService nomenclatureParentGroupService) {
        this.nomenclatureService = nomenclatureService;
        this.nomenclatureParentGroupService = nomenclatureParentGroupService;
    }

    @GetMapping("nomenclature")
    public String nomenclature(Model model) {
        model.addAttribute("nomenclatures", nomenclatureService.findAllNomenclatures());
        model.addAttribute("nomenclatureParentGroups", nomenclatureParentGroupService.findAlNomenclatureParentGroups());
        return "admin/nomenclature/nomenclature";
    }

    @GetMapping("editNomenclature")
    public String editNomenclature(Model model) {
        model.addAttribute("nomenclatureParentGroups", nomenclatureParentGroupService.findAlNomenclatureParentGroups());
        return "admin/nomenclature/editNomenclature";
    }

    @PostMapping("/nomenclature/save")
    public String save(@RequestBody Nomenclature nomenclature) {
        nomenclatureService.save(nomenclature);
        return "redirect:/editNomenclature";
    }

    @PostMapping("/nomenclature/getNomenclatureParentGroup")
    @ResponseBody
    public List<Nomenclature> getNomenclatureParentGroup(@RequestParam String name) {
        return nomenclatureParentGroupService.getNomenclatureParentGroupByName(name).getNomenclatures();
    }
}
