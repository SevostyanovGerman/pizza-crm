package com.pizza.crm.controller.admin;

import com.pizza.crm.model.ScaleOfSize;
import com.pizza.crm.model.ScaleOfSizeValues;
import com.pizza.crm.service.ScaleOfSizeService;
import com.pizza.crm.service.ScaleOfSizeValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ScaleOfSizeController {

    private final ScaleOfSizeService scaleOfSizeService;
    private final ScaleOfSizeValuesService scaleOfSizeValuesService;

    @Autowired
    public ScaleOfSizeController(ScaleOfSizeService scaleOfSizeService, ScaleOfSizeValuesService scaleOfSizeValuesService) {
        this.scaleOfSizeService = scaleOfSizeService;
        this.scaleOfSizeValuesService = scaleOfSizeValuesService;
    }

    @RequestMapping("/te4534543534")
    public String Test(Model model) {
        model.addAttribute("test", scaleOfSizeService.getAll());
        return "test";
    }

    @RequestMapping("/scale_of_size")
    public String getAllScale(Model model) {
        model.addAttribute("scale", scaleOfSizeService.getAll());
        return "scale_of_size";
    }

    @PostMapping("/scale_of_size/addScale")
    public String saveScale(@RequestParam String nameScale) {
        scaleOfSizeService.save(new ScaleOfSize(nameScale));
        return "redirect:/scale_of_size";
    }

    @PostMapping("/scale_of_size/addValues")
    public String addValues(
            @RequestParam String scaleNameValue,
            @RequestParam String nameSize,
            @RequestParam String kitchenSize,
            @RequestParam Boolean defaultSize) {
        ScaleOfSizeValues scaleOfSizeValues = new ScaleOfSizeValues(nameSize, kitchenSize, defaultSize);
        ScaleOfSize scaleOfSize = scaleOfSizeService.findByNameScale(scaleNameValue);
        scaleOfSize.getValuesList().add(scaleOfSizeValues);
        scaleOfSizeService.save(scaleOfSize);
        return "redirect:/scale_of_size";
    }

    @PostMapping(value = "/scale_of_size/changeDefaultSize")
    public String changeDefaultSize(@RequestParam String nameSize, @RequestParam Boolean defaultSize) {
        ScaleOfSizeValues values = scaleOfSizeValuesService.findByNameSize(nameSize);
        values.setDefaultSize(defaultSize);
        scaleOfSizeValuesService.save(values);
        return "redirect:/scale_of_size";
    }

    //возрашаем String т.к при void выдвает ошибку thymeleaf
    // поэтому нужно пеализовать как RestController
    @PostMapping(value = "/scale_of_size/delete/values")
    public String deleteValues(@RequestParam String nameSize){
        ScaleOfSizeValues values = scaleOfSizeValuesService.findByNameSize(nameSize);
        values.setRemoved(true);
        scaleOfSizeValuesService.save(values);
        return "redirect:/scale_of_size";
    }

    @PostMapping(value = "/scale_of_size/delete/scale")
    public String deleteScale(@RequestParam String nameScale){
        scaleOfSizeService.deleteByNameScale(nameScale);
        return "redirect:/scale_of_size";
    }

      @PostMapping(value = "/scale_of_size/edit/values") //переделать!!!
    public String editValues(
            @RequestParam String nameSizeNoEdit,
            @RequestParam String nameSize,
            @RequestParam String kitchenSize,
            @RequestParam Boolean defaultSize){

        ScaleOfSizeValues values = scaleOfSizeValuesService.findByNameSize(nameSizeNoEdit);
        values.setNameSize(nameSize);
        values.setKitchenSize(kitchenSize);
        values.setDefaultSize(defaultSize);
        scaleOfSizeValuesService.save(values);

        return "redirect:/scale_of_size";
    }

    @PostMapping(value = "/scale_of_size/edit/scale")
    public String editScale(
            @RequestParam String nameScaleNoEdit,
            @RequestParam String nameScale){

        ScaleOfSize scale = scaleOfSizeService.findByNameScale(nameScaleNoEdit);
        scale.setName(nameScale);
        scaleOfSizeService.save(scale);

        return "redirect:/scale_of_size";
    }

    void someEndMethod(){

    }
}
