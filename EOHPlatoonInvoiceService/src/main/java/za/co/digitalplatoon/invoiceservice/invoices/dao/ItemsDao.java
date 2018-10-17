package za.co.digitalplatoon.invoiceservice.invoices.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import za.co.digitalplatoon.invoiceservice.invoices.model.Item;

public interface ItemsDao extends JpaRepository<Item, Long> {

	Page<Item> findByInvoice(Long invoicId, Pageable pageable);

}
