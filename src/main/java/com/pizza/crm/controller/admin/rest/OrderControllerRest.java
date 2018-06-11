package com.pizza.crm.controller.admin.rest;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.*;
import com.pizza.crm.model.discount.Discount;
import com.pizza.crm.model.discount.DiscountApplicationMethod;
import com.pizza.crm.model.discount.DiscountMode;
import com.pizza.crm.service.DiscountService;
import com.pizza.crm.service.DishService;
import com.pizza.crm.service.NomenclatureService;
import com.pizza.crm.service.OrderService;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
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

    private final DishService dishService;
    private final DiscountService discountService;
    private final OrderService orderService;
    private final NomenclatureService nomenclatureService;

    public OrderControllerRest(DishService dishService, DiscountService discountService, OrderService orderService,
                                    NomenclatureService nomenclatureService) {
        this.nomenclatureService = nomenclatureService;
        this.dishService = dishService;
        this.discountService = discountService;
        this.orderService = orderService;
    }

    @PostMapping("/admin/discount/getRowTotal")
    public List<Double> getRowTotal(@RequestBody Order order){

        Double rawTotal = 0d;
        Double total = 0d;
        List<Double> rawTotalAndTotal = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();

        //rowTotal cost calculation
        for (int i = 0; i < order.getDishes().size(); i++) {
            rawTotal += order.getDishes().get(i).getAmount() *
                    nomenclatureService.getNomenclatureByName(order.getDishes().get(i).getName()).getPrice();

        }
        total = rawTotal;

        order.setPrice(rawTotal);
        order.setDiscountedPrice(total);
        order.setCreationDate(localDateTime);
        orderService.save(order);

        rawTotalAndTotal.add(order.getPrice());
        rawTotalAndTotal.add(order.getDiscountedPrice());

        return rawTotalAndTotal;
    }

    @PostMapping("/admin/discount/getAllDiscountsForOrder")
    public Collection<Discount> getAllDiscountsForOrder(){
        return discountService.getEnabledDiscounts();
    }
}