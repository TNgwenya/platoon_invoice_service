package za.co.digitalplatoon.invoiceservice.invoices.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.digitalplatoon.invoiceservice.invoices.dao.InvoiceDao;
import za.co.digitalplatoon.invoiceservice.invoices.dao.ItemsDao;
import za.co.digitalplatoon.invoiceservice.invoices.model.Invoice;

@RestController
public class ItemsController {

	private static final Logger logger = LogManager.getLogger(ItemsController.class);

	@Autowired
	ItemsDao itemsDao;

	@Autowired
	InvoicesController invoicesController;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	
	

	
}
