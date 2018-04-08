package com.pizza.crm.service;

import com.pizza.crm.model.Schedule;
import com.pizza.crm.model.discount.Discount;
import com.pizza.crm.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public Collection<Discount> getAll() {
        return discountRepository.findAll();
    }

    @Override
    public Optional<Discount> findById(Long id) {
        return discountRepository.findById(id);
    }

    @Override
    public Discount save(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public Collection<Discount> saveAll(Collection<Discount> discounts) {
        return discountRepository.saveAll(discounts);
    }

    @Override
    public void deleteById(Long id) {
        discountRepository.deleteById(id);
    }

    @Override
    public List<Discount> getActiveDiscount(boolean active, boolean manualInput, int minSum) {
        List<Discount> discounts = discountRepository.getActiveDiscount(active, manualInput, minSum);
        List<Discount> resultDiscount = new ArrayList<>();
        LocalTime now = LocalTime.now();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        Boolean flag = Boolean.TRUE;
        for (Discount d: discounts) {
            if(d.isScheduleRestriction() == true) {
                List<Schedule> schedules = d.getSchedules();
                for (Schedule s: schedules) {
                    if (ChronoUnit.MINUTES.between(s.getBeginTime(), now) >= 0 && ChronoUnit.MINUTES.between(s.getEndTime(), now) <= 0) {
                        switch (dayOfWeek) {
                            case 1:
                                flag = s.getSaturday();
                                break;
                            case 2:
                                flag = s.getMonday();
                                break;
                            case 3:
                                flag = s.getTuesday();
                                break;
                            case 4:
                                flag = s.getWednesday();
                                break;
                            case 5:
                                flag = s.getThursday();
                                break;
                            case 6:
                                flag = s.getFriday();
                                break;
                            case 7:
                                flag = s.getSaturday();
                                break;
                        }
                        if(flag == true) {
                            resultDiscount.add(d);
                        }
                    }
                }
            } else {
                resultDiscount.add(d);
            }

        }
        return resultDiscount;
    }
}
