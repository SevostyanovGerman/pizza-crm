package com.pizza.crm.service;

import com.pizza.crm.model.QuickMenu;

import java.time.DayOfWeek;
import java.util.Collection;

public interface QuickMenuService extends CrudService<QuickMenu, Long> {

    Collection<QuickMenu> getQuickMenuByDay(DayOfWeek day);

    void updateQuickMenu(int day, Collection<QuickMenu> quickMenu);

}
