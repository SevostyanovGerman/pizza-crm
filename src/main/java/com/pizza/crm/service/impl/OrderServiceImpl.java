package com.pizza.crm.service.impl;

import com.pizza.crm.model.Order;
import com.pizza.crm.repository.OrderRepository;
import com.pizza.crm.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements BasicService<Order> {

    private final OrderRepository<Order> orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long aLong) {
        return orderRepository.findById(aLong);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
    @Override
    public List<Order> saveAll(Collection<Order> orders) {
        return orderRepository.saveAll(orders);
    }

    @Override
    public void deleteById(Long aLong) {
        orderRepository.deleteById(aLong);
    }

    @Override
    public Optional<Order> getBy(Object param) {
        return Optional.empty();
    }
}
