package com.pizza.crm.service.impl;

import com.pizza.crm.model.Invoice;
import com.pizza.crm.repository.InvoiceRepository;
import com.pizza.crm.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public void save(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public Optional<Invoice> getInvoiceByOrderId(Long id) {
        return invoiceRepository.getByOrderId(id);
    }


}
