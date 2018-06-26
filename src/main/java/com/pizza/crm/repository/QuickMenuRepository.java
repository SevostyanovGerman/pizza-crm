package com.pizza.crm.repository;

import com.pizza.crm.model.QuickMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface QuickMenuRepository extends JpaRepository<QuickMenu, Long> {

    @Query("SELECT u FROM QuickMenu u WHERE u.weekDay = :weekDay")
    Collection<QuickMenu> getQuickMenuByDayRepository(@Param("weekDay") int weekDay);
}
