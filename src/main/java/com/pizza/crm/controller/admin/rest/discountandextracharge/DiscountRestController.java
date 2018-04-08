package com.pizza.crm.controller.admin.rest.discountandextracharge;


import com.pizza.crm.model.discount.Discount;
import com.pizza.crm.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscountRestController {

    private final DiscountService discountService;

    @Autowired
    public DiscountRestController(DiscountService discountService) {
        this.discountService = discountService;
    }


    @RequestMapping(value = "/get/discount/{minSum}")
    public ResponseEntity<?> getDiscount(@PathVariable int minSum) {
        List<Discount> discounts = discountService.getActiveDiscount(true, true, minSum);
        return ResponseEntity.ok(discounts);
    }
}
