package com.pizza.crm.service;

import com.pizza.crm.model.QuickMenu;

import java.util.Collection;

public interface QuickMenuService extends CrudService<QuickMenu, Long> {

    Collection<QuickMenu> getQuickMenuByDay(int day);

    void updateQuickMenu(int day, Collection<QuickMenu> quickMenu);

}
