package com.pizza.crm.controller.admin.rest.discountandextracharge;

import com.pizza.crm.model.ActionTime;
import com.pizza.crm.model.CategoryDiscount;
import com.pizza.crm.model.Discount;
import com.pizza.crm.model.DiscountAndPayment;
import com.pizza.crm.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class DiscountAndExtraChargeRestController {

    private final DiscountService discountService;

    @Autowired
    public DiscountAndExtraChargeRestController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/discountandextracharge/delete")
    public String delete(@RequestParam String name) {
        discountService.deleteByName(name);
        return "redirect:/discountandextracharge";
    }

    @PostMapping("/discountandextracharge/update")
    public @ResponseBody
    Long update(@RequestParam String name) {
        Discount discountDb = discountService.getByName(name);
        return discountDb.getId();
    }

    @PostMapping("discountandextracharge/get")
    public Discount get(@RequestBody Long id) {
        return discountService.getById(id);
    }

    @PostMapping("discountandextracharge/saveStepOne")
    public Long stepOne(@RequestBody Discount discount) {
        if (discount.getId() == null) {
            return discountService.save(discount);
        }
        Discount discountDb = discountService.getById(discount.getId());
        discountDb.setName(discount.getName());
        discountDb.setCheckName(discount.getCheckName());
        discountDb.setType(discount.getType());
        discountDb.setAcceptManualDiscount(discount.getAcceptManualDiscount());
        discountDb.setMinSum(discount.getMinSum());
        List<ActionTime> timeList = discountDb.getActionTimeList();
        timeList.clear();
        timeList.addAll(discount.getActionTimeList());
        return discountService.save(discountDb);
    }

    @PostMapping("/discountandextracharge/saveStepTwo")
    public Long stepTwo(@RequestBody Map<String, String> options) {
        Discount discount = discountService.getById(Long.parseLong(options.get("id")));
        discount.setManualInput(Boolean.parseBoolean(options.get("manualInput")));
        discount.setSetAuto(Boolean.parseBoolean(options.get("setAuto")));
        discount.setStewardChoice(Boolean.parseBoolean(options.get("stewardChoice")));
        discount.setDiscountWithOther(Boolean.parseBoolean(options.get("discountWithOther")));
        discountService.save(discount);
        return discount.getId();
    }

    @PostMapping("/discountandextracharge/saveStepThree")
    public Long stepThree(@RequestBody Discount discount) {
        Discount discountDb = discountService.getById(discount.getId());
        if (discount.getApplyForAllType()) {
            discountDb.setApplyForAllType(true);
            discountDb.setDiscountType(discount.getDiscountType());
            discountDb.setDiscountValueType(discount.getDiscountValueType());
            discountDb.setValue(discount.getValue());
            discountDb.getCategoryDiscounts().clear();
        } else {
            discountDb.setApplyForAllType(false);
            discountDb.setDiscountType(discount.getDiscountType());
            discountDb.setDiscountValueType(discount.getDiscountValueType());
            discountDb.setValue(discount.getValue());
            List<CategoryDiscount> categoryDiscountList = discountDb.getCategoryDiscounts();
            categoryDiscountList.clear();
            categoryDiscountList.addAll(discount.getCategoryDiscounts());
        }
        return discountService.save(discountDb);
    }

    @PostMapping("/discountandextracharge/saveStepFour")
    public void stepFour(@RequestBody Map<String, String> options) {
        Discount discount = discountService.getById(Long.parseLong(options.get("id")));
        discount.setActive(Boolean.parseBoolean(options.get("active")));
        discountService.save(discount);
    }

    @PostMapping("/discountandextracharge/changeActiveStatus")
    public void changeActiveStatus(@RequestBody Map<String, String> status) {
        Discount discountDb = discountService.getByName(status.get("name"));
        discountDb.setActive(Boolean.parseBoolean(status.get("status")));
        discountService.save(discountDb);
    }

    @PostMapping("/discountandextracharge/changePaymentByCard")
    public void changePaymentByCard(@RequestBody Map<String, String> status) {
        Discount discountDb = discountService.getByName(status.get("name"));
        DiscountAndPayment discountAndPayment = discountDb.getDiscountAndPayment();
        discountAndPayment.setPaymentByCard((Boolean.parseBoolean(status.get("status"))));
        discountService.save(discountDb);
    }

    @PostMapping("/discountandextracharge/changeDiners")
    public void changeDiners(@RequestBody Map<String, String> status) {
        Discount discountDb = discountService.getByName(status.get("name"));
        DiscountAndPayment discountAndPayment = discountDb.getDiscountAndPayment();
        discountAndPayment.setDiners((Boolean.parseBoolean(status.get("status"))));
        discountService.save(discountDb);
    }

    @PostMapping("/discountandextracharge/changeMasterCardElectronics")
    public void changeMasterCardElectronics(@RequestBody Map<String, String> status) {
        Discount discountDb = discountService.getByName(status.get("name"));
        DiscountAndPayment discountAndPayment = discountDb.getDiscountAndPayment();
        discountAndPayment.setMasterCardElectronics((Boolean.parseBoolean(status.get("status"))));
        discountService.save(discountDb);
    }

    @PostMapping("/discountandextracharge/changeVisa")
    public void changeVisa(@RequestBody Map<String, String> status) {
        Discount discountDb = discountService.getByName(status.get("name"));
        DiscountAndPayment discountAndPayment = discountDb.getDiscountAndPayment();
        discountAndPayment.setVisa((Boolean.parseBoolean(status.get("status"))));
        discountService.save(discountDb);
    }

    @PostMapping("/discountandextracharge/changeMasterCard")
    public void changeMasterCard(@RequestBody Map<String, String> status) {
        Discount discountDb = discountService.getByName(status.get("name"));
        DiscountAndPayment discountAndPayment = discountDb.getDiscountAndPayment();
        discountAndPayment.setMasterCard((Boolean.parseBoolean(status.get("status"))));
        discountService.save(discountDb);
    }

    @PostMapping("/discountandextracharge/changeVisaElectron")
    public void changeVisaElectron(@RequestBody Map<String, String> status) {
        Discount discountDb = discountService.getByName(status.get("name"));
        DiscountAndPayment discountAndPayment = discountDb.getDiscountAndPayment();
        discountAndPayment.setVisaElectron((Boolean.parseBoolean(status.get("status"))));
        discountService.save(discountDb);
    }

    @PostMapping("/discountandextracharge/changeMaestro")
    public void changeMaestro(@RequestBody Map<String, String> status) {
        Discount discountDb = discountService.getByName(status.get("name"));
        DiscountAndPayment discountAndPayment = discountDb.getDiscountAndPayment();
        discountAndPayment.setMaestro((Boolean.parseBoolean(status.get("status"))));
        discountService.save(discountDb);
    }

    @PostMapping("/discountandextracharge/changeCash")
    public void changeCash(@RequestBody Map<String, String> status) {
        Discount discountDb = discountService.getByName(status.get("name"));
        DiscountAndPayment discountAndPayment = discountDb.getDiscountAndPayment();
        discountAndPayment.setCash((Boolean.parseBoolean(status.get("status"))));
        discountService.save(discountDb);
    }

    @PostMapping("/discountandextracharge/changeOnlinePayment")
    public void changeOnlinePayment(@RequestBody Map<String, String> status) {
        Discount discountDb = discountService.getByName(status.get("name"));
        DiscountAndPayment discountAndPayment = discountDb.getDiscountAndPayment();
        discountAndPayment.setOnlinePayment((Boolean.parseBoolean(status.get("status"))));
        discountService.save(discountDb);
    }
}
