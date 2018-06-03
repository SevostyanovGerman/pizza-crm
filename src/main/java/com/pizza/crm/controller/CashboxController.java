package com.pizza.crm.controller;

import com.google.gson.Gson;
import com.pizza.crm.model.Dish;
import com.pizza.crm.model.Order;
import com.pizza.crm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/cashbox")
public class CashboxController {

    private static final Logger log = Logger.getLogger("CashboxController");
    private OrderService orderService;

    @Autowired
    public CashboxController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String cahsbox(/*@RequestParam("id") Long orderId,*/ ModelMap mapModel) {
        /*Optional<Order> optionalOrder = orderService.findById(orderId);
        Order order = optionalOrder.orElse(null);*/
        Order order = new Order();
        order.setId(137L);

        Dish pizza = new Dish();
        pizza.setAmount(2L);
        pizza.setName("pizza");
        pizza.setPrice(280d);
        List<Dish> dishList = new ArrayList<>();
        dishList.add(pizza);

        Dish pizza1 = new Dish();
        pizza1.setAmount(1L);
        pizza1.setName("roll");
        pizza1.setPrice(300d);
        dishList.add(pizza1);


        order.setDishes(dishList);
        order.setCostDiscount(100.0d);
        order.setDiscount(10.0d);
        order.setCostNotDiscount(110.0d);
        Gson gson = new Gson();
        mapModel.addAttribute("order", gson.toJson(order));
        log.info("returning admin.html");
        return "/cashbox";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void getPayedOrder() {

    }


}
