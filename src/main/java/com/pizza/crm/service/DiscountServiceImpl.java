package com.pizza.crm.service;

import com.pizza.crm.model.Discount;
import com.pizza.crm.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private DiscountRepository discountRepository;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public Long save(Discount discount) {
        Discount discount1 = discountRepository.save(discount);
        return discount1.getId();
    }

    @Override
    public void delete(Discount discount) {
        discountRepository.delete(discount);
    }

    @Override
    public void deleteByName(String name) {
        discountRepository.deleteByName(name);
    }

    @Override
    public void deleteById(Long id) {
        discountRepository.deleteById(id);
    }

    @Override
    public Discount getByName(String name) {
        return discountRepository.getByName(name);
    }

    @Override
    public Discount getById(Long id) {
        return discountRepository.getById(id);
    }

    @Override
    public List<Discount> findAll() {
        return discountRepository.findAll();
    }
}
