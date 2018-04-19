package com.pizza.crm.controller.admin;

import com.pizza.crm.model.Nomenclature;
import com.pizza.crm.service.NomenclatureParentGroupService;
import com.pizza.crm.service.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        model.addAttribute("nomenclaturesWithoutParentGroup", nomenclatureService.getNomenclaturesWithoutParentGroup());
        return "admin/nomenclature/nomenclature";
    }

    @GetMapping("editNomenclature/{id}")
    public String editNomenclature(@PathVariable Long id, Model model) {
        if (id != null) {
            model.addAttribute("nomenclature", nomenclatureService.getNomenclature(id));
            model.addAttribute("parentGroupName",
                    nomenclatureService.getNomenclature(id).getNomenclatureParentGroupSet().stream().findFirst().get().getName());
        }
        model.addAttribute("nomenclatureParentGroups", nomenclatureParentGroupService.findAlNomenclatureParentGroups());
        model.addAttribute("modifierNomenclatures", nomenclatureService.getNomenclatureModifiers());
        return "admin/nomenclature/editNomenclature";
    }

    @GetMapping("editNomenclature")
    public String createNomenclature(Model model) {
        model.addAttribute("nomenclatureParentGroups", nomenclatureParentGroupService.findAlNomenclatureParentGroups());
        model.addAttribute("modifierNomenclatures", nomenclatureService.getNomenclatureModifiers());
        return "admin/nomenclature/editNomenclature";
    }

    @PostMapping("/nomenclature/save")
    public String save(@RequestBody Nomenclature nomenclature) {
        nomenclatureService.save(nomenclature);
        return "redirect:/editNomenclature";
    }


}
