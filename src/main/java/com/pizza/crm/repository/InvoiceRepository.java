package com.pizza.crm.repository;

import com.pizza.crm.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> getByOrderId(Object orderId);
}
