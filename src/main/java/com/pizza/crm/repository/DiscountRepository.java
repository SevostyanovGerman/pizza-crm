package com.pizza.crm.repository;

import com.pizza.crm.model.discount.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("SELECT d FROM Discount d WHERE d.enabled = true")
    List<Discount> getEnabledDiscounts();

    Discount findByName(String name);

    @Query("SELECT discount " +
            "FROM Discount discount " +
            "WHERE discount.name IN :strings " +
                "AND discount.enabled = true " +
                "AND discount.scheduleRestriction = false " +
                "AND discount.minSumRestriction = false " +
                "AND discount.combinable = true " +

            "OR (discount.name IN :strings " +
                "AND (:total >= discount.minSum) " +
                "AND discount.minSumRestriction = true " +
                "AND discount.enabled = true) " +

            "OR (discount.name IN :strings " +
                "AND (1 = :size) " +
                "AND discount.combinable = false " +
                "AND discount.enabled = true) " +

            "OR discount = (" +
                "SELECT discount " +
                "FROM Discount discount " +
                    "JOIN discount.validities validity " +
                    "JOIN validity.validityScheduleList validitySchedule " +
                    "JOIN validitySchedule.dayOfWeekList dayOfWeek " +
                "WHERE discount.name IN :strings " +
                    "AND dayOfWeek IN :dayOfWeekNow " +
                    "AND (CAST (:localDateTime AS time) between CAST(validitySchedule.beginTime AS time) " +
                        "AND CAST(validitySchedule.endTime AS time))) " +
                    "AND discount.scheduleRestriction = true " +
                    "AND discount.enabled = true "
    )
    List<Discount> getDiscountsForOrder(@Param("strings") List<String> strings,
                                        @Param("dayOfWeekNow") DayOfWeek dayOfWeek,
                                        @Param("localDateTime") LocalDateTime localDateTime,
                                        @Param("total") Double total,
                                        @Param("size") Integer size);
}


