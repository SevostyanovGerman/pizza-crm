package com.pizza.crm.service.impl;

import com.pizza.crm.model.Invoice;
import com.pizza.crm.model.Order;
import com.pizza.crm.repository.InvoiceRepository;
import com.pizza.crm.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements BasicService<Invoice> {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getAll() {
        return null;
    }

    @Override
    public Optional<Invoice> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void save(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> saveAll(Collection<Order> orders) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Invoice> getBy(Object param) {
        return invoiceRepository.getByOrderId(param);
    }

}
