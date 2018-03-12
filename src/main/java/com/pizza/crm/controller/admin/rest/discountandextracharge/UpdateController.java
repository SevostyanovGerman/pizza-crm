package com.pizza.crm.controller.admin.rest.discountandextracharge;

import com.pizza.crm.model.Discount;
import com.pizza.crm.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UpdateController {

    private DiscountService discountService;

    @Autowired
    public UpdateController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("discountandextracharge/save")
    public Long save(@RequestBody Discount discount){
        return discountService.save(discount);
    }

    @PostMapping("discountandextracharge/get")
    public Discount get(@RequestBody Long id){
        return discountService.getById(id);
    }

    @PostMapping("/discountandextracharge/saveAdvancedOptions")
    public Long save(@RequestBody Map<String, String> options){
        Discount discount = discountService.getById(Long.parseLong(options.get("id")));
        discount.setManualInput(Boolean.parseBoolean(options.get("manualInput")));
        discount.setSetAuto(Boolean.parseBoolean(options.get("setAuto")));
        discount.setStewardChoice(Boolean.parseBoolean(options.get("stewardChoice")));
        discount.setDiscountWithOther(Boolean.parseBoolean(options.get("discountWithOther")));
        discountService.save(discount);
        return discount.getId();
    }

    @PostMapping("/discountandextracharge/saveAdvancedOptions3")
    public void save2(@RequestBody Map<String, String> options){
        Discount discount = discountService.getById(Long.parseLong(options.get("id")));
        discount.setActive(Boolean.parseBoolean(options.get("active")));
        discountService.save(discount);
    }
}
