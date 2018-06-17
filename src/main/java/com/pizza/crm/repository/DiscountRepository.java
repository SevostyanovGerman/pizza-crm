package com.pizza.crm.repository;

import com.pizza.crm.model.discount.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("SELECT d FROM Discount d WHERE d.enabled = true")
    List<Discount> getEnabledDiscounts();

    Discount findByName(String name);

    @Query("SELECT discount " +
            "FROM Discount discount " +
            "INNER JOIN discount.validities validity " +
            "INNER JOIN validity.validityScheduleList validitySchedule " +
            "INNER JOIN validitySchedule.dayOfWeekList dayOfWeek " +
            "WHERE discount.name IN :strings AND discount.scheduleRestriction <> true " +
            "OR dayOfWeek IN :dayOfWeekNow"
    )
    List<Discount> getDiscountsForOrder(@Param("strings") List<String> strings,
                                        @Param("dayOfWeekNow") DayOfWeek dayOfWeek);
}


//OR (dayOfWeek IN :dayOfWeekNow)
// AND discount.scheduleRestriction <> true " +
//         "OR (discount.name IN :discounts AND dayOfWeek IN :dayOfWeekNow AND discount.scheduleRestriction = true)

