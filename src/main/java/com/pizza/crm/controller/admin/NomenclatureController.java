package com.pizza.crm.controller.admin;

import com.pizza.crm.model.Nomenclature;
import com.pizza.crm.service.NomenclatureParentGroupService;
import com.pizza.crm.service.NomenclatureService;
import com.pizza.crm.service.UnitsOfMeasurementService;
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

    private final UnitsOfMeasurementService unitsOfMeasurementService;

    private static final String WHITE_BACKGROUND_COLOR = "#FFFFFF";

    private static final String BLACK_FONT_COLOR = "#000000";

    @Autowired
    public NomenclatureController(NomenclatureService nomenclatureService,
                                  NomenclatureParentGroupService nomenclatureParentGroupService,
                                  UnitsOfMeasurementService unitsOfMeasurementService) {
        this.nomenclatureService = nomenclatureService;
        this.nomenclatureParentGroupService = nomenclatureParentGroupService;
        this.unitsOfMeasurementService = unitsOfMeasurementService;
    }

    @GetMapping("nomenclature")
    public String nomenclature(Model model) {
        model.addAttribute("nomenclatures", nomenclatureService.findAllNomenclatures());
        model.addAttribute("nomenclatureParentGroups", nomenclatureParentGroupService.findAlNomenclatureParentGroups());
        model.addAttribute("nomenclaturesWithoutParentGroup", nomenclatureService.getNomenclaturesWithoutParentGroup());
        model.addAttribute("pickFontColor", BLACK_FONT_COLOR);
        model.addAttribute("pickBackgroundColor", WHITE_BACKGROUND_COLOR);
        return "admin/nomenclature/nomenclature";
    }

    @GetMapping("editNomenclature/copyElement/{id}")
    public String copyElement(@PathVariable Long id, Model model) {
        if (id != null) {
            Nomenclature originalNomenclature = nomenclatureService.getNomenclature(id);
            Nomenclature copyNomenclature = new Nomenclature(originalNomenclature.getPrice(), originalNomenclature.getCookingTimeNorm(), originalNomenclature.getCookingTimePeak(),
                    originalNomenclature.getBackgroundColor(), originalNomenclature.getFontColor(), originalNomenclature.getUnitOfMeasurement(), originalNomenclature.getNomenclatureType(),
                    originalNomenclature.getAccountingCategory(), originalNomenclature.getCookingPlace(), originalNomenclature.getNomenclatureParentGroupSet(),
                    originalNomenclature.getModifierPropertyList(), originalNomenclature.getPackagingList());
            model.addAttribute("nomenclature", copyNomenclature);
            if (!copyNomenclature.getNomenclatureParentGroupSet().isEmpty()) {
                model.addAttribute("parentGroupName",
                        copyNomenclature.getNomenclatureParentGroupSet().iterator().next().getName());
            }
        }
        model.addAttribute("unitsOfMeasurement", unitsOfMeasurementService.getAll());
        model.addAttribute("nomenclatureParentGroups", nomenclatureParentGroupService.findAlNomenclatureParentGroups());
        model.addAttribute("modifierNomenclatures", nomenclatureService.getNomenclatureModifiers());
        return "admin/nomenclature/editNomenclature";

    }

    @GetMapping("editNomenclature/{id}")
    public String editNomenclature(@PathVariable Long id, Model model) {
        if (id != null) {
            model.addAttribute("nomenclature", nomenclatureService.getNomenclature(id));
            if (!nomenclatureService.getNomenclature(id).getNomenclatureParentGroupSet().isEmpty()) {
                model.addAttribute("parentGroupName",
                        nomenclatureService.getNomenclature(id).getNomenclatureParentGroupSet().iterator().next().getName());
            }

            String pickBackgroundColor = nomenclatureService.getNomenclature(id).getBackgroundColor();
            if (pickBackgroundColor == null) {
                pickBackgroundColor = WHITE_BACKGROUND_COLOR;
            }
            model.addAttribute("pickBackgroundColor", pickBackgroundColor);

            String pickFontColor = nomenclatureService.getNomenclature(id).getFontColor();
            if (pickFontColor == null) {
                pickFontColor = BLACK_FONT_COLOR;
            }
            model.addAttribute("pickFontColor", pickFontColor);
        }
        model.addAttribute("unitsOfMeasurement", unitsOfMeasurementService.getAll());
        model.addAttribute("nomenclatureParentGroups", nomenclatureParentGroupService.findAlNomenclatureParentGroups());
        model.addAttribute("modifierNomenclatures", nomenclatureService.getNomenclatureModifiers());
        return "admin/nomenclature/editNomenclature";
    }

    @GetMapping("editNomenclature")
    public String createNomenclature(Model model) {
        model.addAttribute("nomenclatureParentGroups", nomenclatureParentGroupService.findAlNomenclatureParentGroups());
        model.addAttribute("modifierNomenclatures", nomenclatureService.getNomenclatureModifiers());
        model.addAttribute("unitsOfMeasurement", unitsOfMeasurementService.getAll());
        return "admin/nomenclature/editNomenclature";
    }

    @PostMapping("/nomenclature/save")
    public String save(@RequestBody Nomenclature nomenclature) {
        nomenclatureService.save(nomenclature);
        return "redirect:/editNomenclature";
    }


}
