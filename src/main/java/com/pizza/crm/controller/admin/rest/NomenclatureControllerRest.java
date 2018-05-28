package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.model.Nomenclature;
import com.pizza.crm.model.NomenclatureParentGroup;
import com.pizza.crm.service.NomenclatureParentGroupService;
import com.pizza.crm.service.NomenclatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NomenclatureControllerRest {

    private final NomenclatureService nomenclatureService;

    private final NomenclatureParentGroupService nomenclatureParentGroupService;

    @Autowired
    public NomenclatureControllerRest(NomenclatureService nomenclatureService,
                                      NomenclatureParentGroupService nomenclatureParentGroupService) {
        this.nomenclatureService = nomenclatureService;
        this.nomenclatureParentGroupService = nomenclatureParentGroupService;
    }

    @PostMapping("/nomenclature/getNomenclatureParentGroup")
    public List<Nomenclature> getNomenclatureParentGroup(@RequestParam String name) {
        return nomenclatureParentGroupService.getNomenclatureParentGroupByName(name).getNomenclatures();
    }

    @PostMapping("/nomenclature/changeParentGroup")
    public void changeParentGroup(@RequestParam String name, @RequestParam String parentGroupName) {
        Nomenclature nomenclature = nomenclatureService.getNomenclatureByName(name);
        nomenclature.getNomenclatureParentGroupSet().clear();
        nomenclature.getNomenclatureParentGroupSet()
                .add(nomenclatureParentGroupService.getNomenclatureParentGroupByName(parentGroupName));
        nomenclatureService.save(nomenclature);
    }

    @PostMapping("/nomenclature/saveParentGroup")
    public void saveParentGroup(@RequestBody NomenclatureParentGroup nomenclatureParentGroup) {
        nomenclatureParentGroupService.save(nomenclatureParentGroup);
    }

    @PostMapping("/nomenclature/getNomenclatureId")
    public Long getId(@RequestParam String name) {
        return nomenclatureService.getNomenclatureByName(name).getId();
    }

    @PostMapping("nomenclature/delete")
    public void deleteNomenclature(String name) {
        Nomenclature nomenclature = nomenclatureService.getNomenclatureByName(name);
        nomenclature.setRemoved(true);
        nomenclatureService.save(nomenclature);
    }

    @PostMapping("nomenclature/restoration")
    public void restoration(String name) {
        Nomenclature nomenclature = nomenclatureService.getNomenclatureByName(name);
        nomenclature.setRemoved(false);
        nomenclatureService.save(nomenclature);
    }

    @PostMapping("/getModifiers")
    public List <Nomenclature> getModifiers(@RequestParam String name) {
        NomenclatureParentGroup nomenclatureParentGroup = nomenclatureParentGroupService.getNomenclatureParentGroupByName(name);
        return nomenclatureParentGroup.getNomenclatures();
    }



}
