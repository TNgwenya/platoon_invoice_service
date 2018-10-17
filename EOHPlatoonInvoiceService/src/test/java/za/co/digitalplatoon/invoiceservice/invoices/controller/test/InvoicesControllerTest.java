package za.co.digitalplatoon.invoiceservice.invoices.controller.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import za.co.digitalplatoon.invoiceservice.invoices.controller.InvoicesController;
import za.co.digitalplatoon.invoiceservice.invoices.dao.InvoiceDao;
import za.co.digitalplatoon.invoiceservice.invoices.model.Invoice;

@SpringBootTest
public class InvoicesControllerTest {

	private MockMvc mockMvc;

	@Mock
	InvoiceDao invoiceDao = mock(InvoiceDao.class);

	@InjectMocks
	private InvoicesController invoicesController;

	@Before(value = "")
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(invoicesController).build();
	}

	/* Test method to test adding a new invoice */
	@Test
	public void testAddInvoice() throws Exception {

		Invoice invoice = new Invoice(1, "John", java.sql.Date.valueOf("2005-05-05"), 11);

		when(invoiceDao.save(invoice)).thenReturn(invoice);

		mockMvc.perform(post("/invoices")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].client", is("John")))
				.andExpect(header().string("location", containsString("http://localhost/invoices/")));

		verify(invoiceDao, times(1)).findById((invoice.getId()));
		verifyNoMoreInteractions(invoiceDao);
	}
	
	  @Test public void testgetAllInvoices() throws Exception {
	  
	  
	  
	  List<Invoice> invoices = Arrays.asList(new Invoice(1, "thabang",
	  java.sql.Date.valueOf("2018-05-05"), 11), new Invoice(2, "blessings",
	  java.sql.Date.valueOf("2017-08-10"), 16), new Invoice(3, "david",
	  java.sql.Date.valueOf("2015-10-10"), 23));
	  
	  when(invoiceDao.findAll()).thenReturn(invoices);
	  
	  mockMvc.perform(get("/invoices")) .andExpect(status().isOk())
	  .andExpect(jsonPath("$[0].id", is(1))) .andExpect(jsonPath("$[0].client",
	  is("thabang")));
	  
	  verify(invoiceDao, times(1)).findAll(); verifyNoMoreInteractions(invoiceDao);
	  }
	 

	
	  @Test public void testgetInvoiceById() throws Exception {
	  
	  
	  
	  Invoice invoice = new Invoice(1, "thabang",
	  java.sql.Date.valueOf("2018-05-05"), 11);
	  
	   Optional<Invoice> inv = invoiceDao.findById((long) 1);
	  
	  when(invoiceDao.findById((long)1)).thenReturn(inv);
	  
	  mockMvc.perform(get("/invoices/{id}/",1L)).andDo(print())
	  .andExpect(status().isOk()) .andExpect(jsonPath("$.id", is(1)))
	  .andExpect(jsonPath("$.client", is("thabang")));
	  
	  verify(invoiceDao, times(1)).findById((long) 1);
	  verifyNoMoreInteractions(invoiceDao);
	  
	  }
	 

}
