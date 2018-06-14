package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.Validity;
import com.pizza.crm.model.discount.Discount;
import com.pizza.crm.service.DiscountService;
import com.pizza.crm.service.ValidityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class DiscountControllerRest {

    private final ValidityService validityService;
    private final DiscountService discountService;

    public DiscountControllerRest(ValidityService validityService, DiscountService discountService) {
        this.validityService = validityService;
        this.discountService = discountService;
    }

    @PostMapping("/admin/discount/getValidity")
    public Validity getSchedule(@RequestBody Validity validity) {
        return validityService.findById(validity.getId()).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/admin/discount/getValidityForDiscount")
    public List<Validity> getValidityForDiscount(@RequestBody Discount discount) {
        Discount discountResponse = discountService.findByName(discount.getName());
        return discountResponse.getValidities();
    }

    @PostMapping("/admin/discount/getAllDiscounts")
    public Collection<Discount> getAllDiscounts() {
        return discountService.getAll();
    }

}
