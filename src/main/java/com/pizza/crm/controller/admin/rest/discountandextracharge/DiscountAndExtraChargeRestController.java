package com.pizza.crm.controller.admin.rest.discountandextracharge;

import com.pizza.crm.model.Discount;
import com.pizza.crm.model.DiscountAndPayment;
import com.pizza.crm.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @ResponseBody
    public Long update(@RequestParam String name) {
        Discount discountDb = discountService.getByName(name);
        return discountDb.getId();
    }

    @PostMapping("discountandextracharge/get")
    public Discount get(@RequestBody Long id) {
        return discountService.getById(id);
    }

    @PostMapping("/discountandextracharge/save")
    public void save(@RequestBody Discount discount) {
        discountService.save(discount);
    }

    @PostMapping("/discountandextracharge/changeActiveStatus")
    public void changeActiveStatus(@RequestBody Map<String, String> status) {
        Discount discountDb = discountService.getByName(status.get("name"));
        discountDb.setActive(Boolean.parseBoolean(status.get("status")));
        discountService.save(discountDb);
    }

    @PostMapping("/discountandextracharge/changePayment")
    public void changePayment(@RequestBody Map<String, String> request) {
        Discount discountDb = discountService.getByName(request.get("name"));
        DiscountAndPayment discountAndPayment = discountDb.getDiscountAndPayment();
        String paymentType = request.get("paymentType");
        Boolean status = Boolean.parseBoolean(request.get("status"));
        switch (paymentType) {
            case "paymentByCard":
                discountAndPayment.setPaymentByCard(status);
                break;
            case "diners":
                discountAndPayment.setDiners(status);
                break;
            case "masterCardElectronics":
                discountAndPayment.setMasterCardElectronics(status);
                break;
            case "visa":
                discountAndPayment.setVisa(status);
                break;
            case "masterCard":
                discountAndPayment.setMasterCard(status);
                break;
            case "visaElectron":
                discountAndPayment.setVisaElectron(status);
                break;
            case "maestro":
                discountAndPayment.setMaestro(status);
                break;
            case "cash":
                discountAndPayment.setCash(status);
                break;
            case "onlinePayment":
                discountAndPayment.setOnlinePayment(status);
                break;
            default:
                break;
        }
        discountService.save(discountDb);
    }
}
