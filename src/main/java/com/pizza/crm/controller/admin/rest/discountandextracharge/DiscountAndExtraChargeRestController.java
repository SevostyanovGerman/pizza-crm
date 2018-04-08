package com.pizza.crm.controller.admin.rest.discountandextracharge;

//import com.pizza.crm.model.ActionTime;
//import com.pizza.crm.model.CategoryDiscount;
//import com.pizza.crm.model.Category;
//import com.pizza.crm.model.Discount;
//import com.pizza.crm.service.DiscountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class DiscountAndExtraChargeRestController {
//
//    private final DiscountService discountService;
//
//    @Autowired
//    public DiscountAndExtraChargeRestController(DiscountService discountService) {
//        this.discountService = discountService;
//    }
//
//    @PostMapping("/discountandextracharge/delete")
//    public String delete(@RequestParam String name) {
//        discountService.deleteByName(name);
//        return "redirect:/discountandextracharge";
//    }
//
//    @PostMapping("/discountandextracharge/update")
//    @ResponseBody
//    public Long update(@RequestParam String name) {
//        Discount discountDb = discountService.getByName(name);
//        return discountDb.getId();
//    }
//
//    @PostMapping("discountandextracharge/get")
//    public Discount get(@RequestBody Long id) {
//        return discountService.getById(id);
//    }
//
//    @PostMapping("/discountandextracharge/save")
//    public void save(@RequestBody Discount discount) {
//        discountService.save(discount);
//    }
//
//    @PostMapping("/discountandextracharge/changeActiveStatus")
//    public void changeActiveStatus(@RequestBody Map<String, String> status) {
//        Discount discountDb = discountService.getByName(status.get("name"));
//        discountDb.setActive(Boolean.parseBoolean(status.get("status")));
//        discountService.save(discountDb);
//    }
//
//    @PostMapping("/discountandextracharge/changePayment")
//    public void changePayment(@RequestParam String name, @RequestParam String status,
//                              @RequestParam String paymentType) {
//        Discount discountDb = discountService.getByName(name);
//        if (Boolean.parseBoolean(status)) {
//            discountDb.getDiscountAndPayment().getPaymentTypes().add(paymentType);
//        } else {
//            discountDb.getDiscountAndPayment().getPaymentTypes().remove(paymentType);
//        }
//        discountService.save(discountDb);
//    }
//
//    @RequestMapping(value = "/get/discount")
//    public ResponseEntity<?> getDiscount() {
//        List<Discount> discounts = discountService.getActiveDiscount(true, false);
//        return ResponseEntity.ok(discounts);
//    }
//}
