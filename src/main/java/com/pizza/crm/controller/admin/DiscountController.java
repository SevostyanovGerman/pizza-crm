package com.pizza.crm.controller.admin;

import com.pizza.crm.exceptions.NotFoundException;
import com.pizza.crm.model.Category;
import com.pizza.crm.model.PaymentMethod;
import com.pizza.crm.model.PaymentType;
import com.pizza.crm.model.Schedule;
import com.pizza.crm.model.discount.*;
import com.pizza.crm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class DiscountController {

    private final DiscountService discountService;
    private final PaymentMethodService paymentMethodService;
    private final ScheduleService scheduleService;
    private final CategoryService categoryService;

    @Autowired
    public DiscountController(DiscountService discountService, PaymentMethodService paymentMethodService,
                              ScheduleService scheduleService, CategoryService categoryService) {
        this.discountService = discountService;
        this.paymentMethodService = paymentMethodService;
        this.scheduleService = scheduleService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/discount/list")
    public String getAllDiscounts(Model model) {
        model.addAttribute("allDiscounts", discountService.getAll());
        model.addAttribute("allPaymentMethods", paymentMethodService.getAll());
        return "/admin/discount/listDiscount";
    }

    @GetMapping("/admin/discount/edit/{id}")
    public String getDiscount(@PathVariable Long id, Model model) {
        Discount discount = discountService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("discount", discount);

// Enums creating
        model.addAttribute("FIRST_DISH", DiscountAssignMode.FIRST_DISH);
        model.addAttribute("ALL_DISHES", DiscountAssignMode.ALL_DISHES);

        model.addAttribute("FULL_PRICE", DiscountApplicationMethod.FULL_PRICE);
        model.addAttribute("WITH_OTHERS", DiscountApplicationMethod.WITH_OTHERS);

        model.addAttribute("DISCOUNT", DiscountMode.DISCOUNT);
        model.addAttribute("EXTRA_PAY", DiscountMode.EXTRA_PAY);
        model.addAttribute("NOT_APPLICABLE", DiscountMode.NOT_APPLICABLE);

        model.addAttribute("PERCENT", DiscountCalculationMode.PERCENT);
        model.addAttribute("FIXED", DiscountCalculationMode.FIXED);

// Schedules creating
        model.addAttribute("allSchedules", scheduleService.findAllSchedules());

        // discountCategories creating
        if (discount.getDiscountCategories().isEmpty()) {
            List<Category> categories = new ArrayList<>(categoryService.getAll());
            List<DiscountCategory> discountCategories = new ArrayList<>();
            for (Category category:categories) {
                DiscountCategory discountCategory = new DiscountCategory();
                discountCategory.setName(category.getName());
                discountCategories.add(discountCategory);
            }
            discount.setDiscountCategories(discountCategories);

        }

        return "/admin/discount/saveDiscount";
    }

    @GetMapping("/admin/discount/new")
    public String newDiscount(Model model) {
// Discount creating
        Discount discount = new Discount();
        model.addAttribute("discount", discount);

// Enums creating
        model.addAttribute("FIRST_DISH", DiscountAssignMode.FIRST_DISH);
        model.addAttribute("ALL_DISHES", DiscountAssignMode.ALL_DISHES);

        model.addAttribute("FULL_PRICE", DiscountApplicationMethod.FULL_PRICE);
        model.addAttribute("WITH_OTHERS", DiscountApplicationMethod.WITH_OTHERS);

        model.addAttribute("DISCOUNT", DiscountMode.DISCOUNT);
        model.addAttribute("EXTRA_PAY", DiscountMode.EXTRA_PAY);
        model.addAttribute("NOT_APPLICABLE", DiscountMode.NOT_APPLICABLE);

        model.addAttribute("PERCENT", DiscountCalculationMode.PERCENT);
        model.addAttribute("FIXED", DiscountCalculationMode.FIXED);

// Schedules creating
        model.addAttribute("allSchedules", scheduleService.findAllSchedules());

// discountCategories creating
        List<Category> categories = new ArrayList<>(categoryService.getAll());
        List<DiscountCategory> discountCategories = new ArrayList<>();
        for (Category category:categories) {
            DiscountCategory discountCategory = new DiscountCategory();
            discountCategory.setName(category.getName());
            discountCategories.add(discountCategory);
        }
        discount.setDiscountCategories(discountCategories);

        return "/admin/discount/saveDiscount";
    }

    @PostMapping("/admin/discount/save")
    public void saveDiscount(@RequestBody Discount discount){

// PaymentMethods creating
        List<PaymentMethod> paymentMethods = new ArrayList<>(paymentMethodService.getAll());
        discount.setPaymentMethods(paymentMethods);

        discountService.save(discount);
    }

    @DeleteMapping("/admin/discount/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDiscount(@PathVariable Long id) {
        discountService.deleteById(id);
    }

    @PostMapping("/admin/discount/methods")
    public String listPaymentMethods(@RequestBody Discount discount){
        Discount discount1 = discountService.findById(discount.getId()).orElseThrow(NotFoundException::new);
        if (!discount.getEnabled()) {
            discount1.getPaymentMethods().remove(paymentMethodService.getPaymentMethodByName(discount.getName()));
        } else {
            discount1.getPaymentMethods().add(paymentMethodService.getPaymentMethodByName(discount.getName()));
        }
        discountService.save(discount1);
        return "redirect:/admin/discount/list";
    }
}
