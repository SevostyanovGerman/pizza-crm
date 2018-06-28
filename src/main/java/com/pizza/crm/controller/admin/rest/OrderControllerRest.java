package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.*;
import com.pizza.crm.model.discount.Discount;
import com.pizza.crm.model.discount.DiscountApplicationMethod;
import com.pizza.crm.model.discount.DiscountMode;
import com.pizza.crm.service.DiscountService;
import com.pizza.crm.service.NomenclatureService;
import com.pizza.crm.service.OrderService;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class OrderControllerRest {

    private final DiscountService discountService;
    private final OrderService orderService;
    private final NomenclatureService nomenclatureService;

    public OrderControllerRest(DiscountService discountService, OrderService orderService,
                                    NomenclatureService nomenclatureService) {
        this.nomenclatureService = nomenclatureService;
        this.discountService = discountService;
        this.orderService = orderService;
    }

    @PostMapping("/discount/getRowTotal")
    public List<Double> getRowTotal(@RequestBody Order order){

        Double rawTotal = 0d;
        Double total = 0d;
        List<Double> rawTotalAndTotal = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Nomenclature> nomenclatures = new ArrayList<>();
        DayOfWeek dayOfWeekNow = LocalDate.now().getDayOfWeek();
        Double discountSum = 0d;
        Double extraChargeSum = 0d;
        List<String> nameDiscounts = new ArrayList<>();

        //rowTotal cost calculation
        for (Nomenclature nomenclature: order.getNomenclatures()) {
            Nomenclature nom = nomenclatureService.getNomenclatureByName(nomenclature.getName());
            nom.setAmount(nomenclature.getAmount());
            nomenclatures.add(nom);

            rawTotal += nom.getAmount() * nom.getPrice();
        }
        total = rawTotal;

        for (Discount d : order.getDiscounts()) {
            nameDiscounts.add(d.getName());
        }

        order.setDiscounts(discountService.getDiscountsForOrder(nameDiscounts, dayOfWeekNow, localDateTime,
                                                                rawTotal, nameDiscounts.size()));
        
        //Order cost calculation with discounts
        if (order.getDiscounts() != null) {
            for (Discount discount: order.getDiscounts()) {
                switch (discount.getDiscountApplicationMethod()) {
                    case FULL_PRICE:
                        if (discount.getDiscountMode() == DiscountMode.DISCOUNT) {
                            total = total - (rawTotal * discount.getValue()) / 100;
                            discountSum += discount.getValue();
                        } else if (discount.getDiscountMode() == DiscountMode.EXTRA_PAY) {
                            total = total + (rawTotal * discount.getValue()) / 100;
                            extraChargeSum += discount.getValue();
                        }
                        break;
                    case WITH_OTHERS:
                        if (discount.getDiscountMode() == DiscountMode.DISCOUNT) {
                            total = total - (total * discount.getValue()) / 100;
                            discountSum += discount.getValue();
                        } else if (discount.getDiscountMode() == DiscountMode.EXTRA_PAY) {
                            total = total + (total * discount.getValue()) / 100;
                            extraChargeSum += discount.getValue();
                        }
                        break;
                }
            }
        }

        order.setPrice(rawTotal);
        order.setDiscountedPrice(total);
        order.setDiscountCost(discountSum);
        order.setExtraChargeCost(extraChargeSum);
        order.setCreationDate(localDateTime);
        order.setNomenclatures(nomenclatures);
        orderService.save(order);

        rawTotalAndTotal.add(order.getPrice());
        rawTotalAndTotal.add(order.getDiscountedPrice());
        rawTotalAndTotal.add(order.getDiscountCost());
        rawTotalAndTotal.add(order.getExtraChargeCost());

        return rawTotalAndTotal;
    }

    @PostMapping("/discount/getAllDiscountsForOrder")
    public Collection<Discount> getAllDiscountsForOrder(){
        return discountService.getEnabledDiscounts();
    }
}