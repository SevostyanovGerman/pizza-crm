package com.pizza.crm.repository;

import com.pizza.crm.model.QuickMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.Collection;

public interface QuickMenuRepository extends JpaRepository<QuickMenu, Long> {

    @Query("SELECT u FROM QuickMenu u WHERE u.dayOfWeek = :dayOfWeek")
    Collection<QuickMenu> getQuickMenuByDayRepository(@Param("dayOfWeek") DayOfWeek dayOfWeek);
}
