package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.model.ScaleOfSize;
import com.pizza.crm.model.ScaleOfSizeValues;
import com.pizza.crm.service.ScaleOfSizeService;
import com.pizza.crm.service.ScaleOfSizeValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ScaleOfSizeControllerRest {

    private final ScaleOfSizeService scaleOfSizeService;

    @Autowired
    public ScaleOfSizeControllerRest(ScaleOfSizeService scaleOfSizeService) {
        this.scaleOfSizeService = scaleOfSizeService;
    }

    @PostMapping("/scale_of_size/getValues")
    public List <ScaleOfSizeValues> getScaleOfSizeValues(@RequestParam String scaleName) {
        ScaleOfSize byNameScale = scaleOfSizeService.findByNameScale(scaleName);
        return byNameScale.getValuesList();
    }


}
