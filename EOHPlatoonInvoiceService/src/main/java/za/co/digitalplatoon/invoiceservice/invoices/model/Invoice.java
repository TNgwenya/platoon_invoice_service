package za.co.digitalplatoon.invoiceservice.invoices.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "Invoice")
public class Invoice {

	private static final Logger log = LoggerFactory.getLogger(Invoice.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id", unique = true, nullable = false, updatable = false)
	private long id;

	@Column(name = "client_name")
	private String client;

	@Column(name = "invoice_date")
	private Date invoiceDate;

	@Column(name = "vat_rate")
	private long vatRate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "invoice")
	private List<Item> items = new ArrayList<Item>();

	public Invoice() {
		super();
	}

	public Invoice(String client) {
		super();
		this.client = client;
	}

	public Invoice(String client, Date invoiceDate, long vatRate) {
		super();
		this.client = client;
		this.invoiceDate = invoiceDate;
		this.vatRate = vatRate;

	}

	public Invoice(long id, String client, Date invoiceDate, long vatRate) {
		super();
		this.id = id;
		this.client = client;
		this.invoiceDate = invoiceDate;
		this.vatRate = vatRate;

	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public long getVatRate() {
		return vatRate;
	}

	public void setVatRate(long vatRate) {
		this.vatRate = vatRate;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public List<Item> getItem() {
		return items;
	}

	public void setItem(List<Item> items) {
		this.items = items;
	}

	public BigDecimal getSubTotal() {

		double multiplier = (double) getVatRate() / 100;
		long quantity = 0;
		long price = 0;

		for (Item item : items) {
			quantity = quantity + item.getQuantity();
			price = item.getUnitPrice();
		}
		log.info("quantity is = " + quantity);
		log.info("price is = " + price);

		BigDecimal bigDecimalPrice = new BigDecimal(price);
		BigDecimal bigDecimalQuantity = new BigDecimal(quantity);

		BigDecimal amount = bigDecimalPrice.multiply(bigDecimalQuantity);

		BigDecimal vatAmount = amount.multiply(new BigDecimal(multiplier));

		BigDecimal amountPlusVat = amount.add(vatAmount);

		return amountPlusVat.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVat() {

		double multiplier = (double) getVatRate() / 100;
		long quantity = 0;
		long price = 0;

		for (Item item : items) {
			quantity = quantity + item.getQuantity();
			price = item.getUnitPrice();
		}
		log.info("quantity is = " + quantity);
		log.info("price is = " + price);

		BigDecimal bigDecimalPrice = new BigDecimal(price);
		BigDecimal bigDecimalQuantity = new BigDecimal(quantity);

		BigDecimal amount = bigDecimalPrice.multiply(bigDecimalQuantity);

		BigDecimal vatAmount = amount.multiply(new BigDecimal(multiplier));

		BigDecimal amountPlusVat = amount.add(vatAmount);

		return vatAmount.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getTotal() {

		long quantity = 0;
		long price = 0;

		// Iterate throigh items to get quantity
		for (Item item : items) {
			quantity = quantity + item.getQuantity();
			price = item.getUnitPrice();
		}
		BigDecimal bigDecimalPrice = new BigDecimal(price);
		BigDecimal bigDecimalQuantity = new BigDecimal(quantity);

		return (bigDecimalPrice.multiply(bigDecimalQuantity)).setScale(2, RoundingMode.HALF_UP);
	}

}
