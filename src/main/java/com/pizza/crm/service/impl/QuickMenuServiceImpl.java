package com.pizza.crm.service.impl;

import com.pizza.crm.model.NomenclatureQuickMenu;
import com.pizza.crm.model.QuickMenu;
import com.pizza.crm.repository.DishQuickMenuRepository;
import com.pizza.crm.repository.QuickMenuRepository;
import com.pizza.crm.service.QuickMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.Optional;

@Service
public class QuickMenuServiceImpl implements QuickMenuService {

    private final QuickMenuRepository quickMenuRepository;

    private final DishQuickMenuRepository dishQuickMenuRepository;

    @Autowired
    public QuickMenuServiceImpl(QuickMenuRepository quickMenuRepository, DishQuickMenuRepository dishQuickMenuRepository) {
        this.dishQuickMenuRepository = dishQuickMenuRepository;
        this.quickMenuRepository = quickMenuRepository;
    }

    @Override
    public QuickMenu save(QuickMenu quickMenu) {
        return quickMenuRepository.save(quickMenu);
    }

    @Override
    public Collection<QuickMenu> getAll() {
        return quickMenuRepository.findAll();
    }

    @Override
    public Optional<QuickMenu> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Collection<QuickMenu> saveAll(Collection<QuickMenu> quickMenus) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Collection<QuickMenu> getQuickMenuByDay(DayOfWeek day) {
        return quickMenuRepository.getQuickMenuByDayRepository(day);
    }

    @Override
    public void updateQuickMenu(int day, Collection<QuickMenu> quickMenus) {
        for (QuickMenu quickMenu : quickMenus) {
            for (NomenclatureQuickMenu nomenclatureQuickMenu : quickMenu.getNomenclatureQuickMenus()) {
                if (nomenclatureQuickMenu != null) {
                    dishQuickMenuRepository.save(nomenclatureQuickMenu);
                }
            }
            quickMenuRepository.save(quickMenu);
        }
    }
}
