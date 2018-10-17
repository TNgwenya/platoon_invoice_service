package za.co.digitalplatoon.invoiceservice.invoices.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import za.co.digitalplatoon.invoiceservice.invoices.model.Invoice;


public interface InvoiceDao extends JpaRepository<Invoice, Long> {




}
