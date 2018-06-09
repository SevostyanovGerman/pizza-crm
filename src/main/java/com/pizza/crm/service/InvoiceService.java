package com.pizza.crm.service;

import com.pizza.crm.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    List<Invoice> getInvoices();

    void save(Invoice invoice);

    Optional<Invoice> getInvoiceByOrderId(Long id);


}
