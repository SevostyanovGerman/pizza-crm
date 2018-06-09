package com.pizza.crm.controller;

import com.google.gson.Gson;
import com.pizza.crm.model.*;
import com.pizza.crm.service.InvoiceService;
import com.pizza.crm.service.OrderService;
import com.pizza.crm.service.PaymentService;
import com.pizza.crm.service.PaymentTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

@Controller
@RequestMapping("/cashbox")
public class CashboxController {

    private static final Logger log = Logger.getLogger("CashboxController");
    private OrderService orderService;
    private PaymentTypeService paymentTypeService;
    private PaymentService paymentService;
    private InvoiceService invoiceService;
    private Map<Long, Payment> allPayments;
    private List<Payment> orderPayment;

    public CashboxController(OrderService orderService, PaymentTypeService paymentTypeService, PaymentService paymentService, InvoiceService invoiceService) {
        this.orderService = orderService;
        this.paymentTypeService = paymentTypeService;
        this.paymentService = paymentService;
        this.invoiceService = invoiceService;
        allPayments = new HashMap<>();
        orderPayment = new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String cahsbox(/*@RequestParam("id") Long orderId,*/ ModelMap mapModel) {
        /*Optional<Order> optionalOrder = orderService.findById(orderId);
        Order order = optionalOrder.orElse(null);*/

        Order order = getOrder();
        Gson gson = new Gson();
        mapModel.addAttribute("order", gson.toJson(order));
        log.info("returning admin.html");
        return "/cashbox";
    }

    // TODO: 09.06.2018 Удалить создание тестового ордера после того, как будет сделана передача id
    private Order getOrder() {
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
        return order;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void getPayedOrder(
            @RequestParam("id") Long orderId,
            @RequestParam("total") Double total,
            @RequestParam("cash") Double cash,
            @RequestParam("totalCash") Double totalCash,
            @RequestParam("change") Double change,
            @RequestParam("currentPaymentMethod") String currentPaymentMethod,
            @RequestParam("paid") Boolean paid) {
        System.out.println("orderId: " + orderId);
        System.out.println("total: " + total);
        System.out.println("cash: " + cash);
        System.out.println("totalCash: " + totalCash);
        System.out.println("change: " + change);
        System.out.println("currentPaymentMethod: " + currentPaymentMethod);
        System.out.println("paid: " + paid);

        Payment payment = new Payment();
        PaymentType paymentType = paymentTypeService.findByName(currentPaymentMethod).get();
        payment.setCash(cash);
        payment.setPaymentType(paymentType);
        orderPayment.add(payment);

        if (paid) {
            Invoice invoiceWithPayment = new Invoice();
            invoiceWithPayment.setDateCreate(LocalDateTime.now());
            invoiceWithPayment.setOrderId(orderId);
            for (Payment pmt : orderPayment) {
                pmt.setInvoice(invoiceWithPayment);
            }
            invoiceWithPayment.setPayments(orderPayment);
            invoiceService.save(invoiceWithPayment);
        }
        System.out.println("Invoice id = " + invoiceService.getInvoiceByOrderId(orderId).orElse(null));
        System.out.println(orderPayment.size());
    }
}
