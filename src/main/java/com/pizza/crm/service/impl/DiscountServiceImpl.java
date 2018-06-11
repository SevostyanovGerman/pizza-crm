package com.pizza.crm.service.impl;

import com.pizza.crm.model.discount.Discount;
import com.pizza.crm.repository.DiscountRepository;
import com.pizza.crm.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    public List<Discount> getEnabledDiscounts() {
        return discountRepository.getEnabledDiscounts();
    }

    @Override
    public Discount findByName(String name) {
        return discountRepository.findByName(name);
    }
}
