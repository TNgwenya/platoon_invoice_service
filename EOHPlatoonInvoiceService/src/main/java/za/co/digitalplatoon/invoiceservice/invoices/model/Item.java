package za.co.digitalplatoon.invoiceservice.invoices.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_Id", unique = true, nullable = false, updatable = false)
	private long itemId;

	@Column(name = "quantity")
	private long quantity;

	@Column(name = "description")
	private String description;

	@Column(name = "unit_Price")
	private long unitPrice;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private Invoice invoice;

	
	
	public Item() {
		super();
	}

	public Item(String description, Invoice invoice) {
		this.description = description;
		this.invoice = invoice;
	}

	public Item(long itemId, long quantity, String description, long unitPrice) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.description = description;
		this.unitPrice = unitPrice;
	}

	
	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	
	public BigDecimal getLineItemTotal() {

		return new BigDecimal(getQuantity()).multiply(new BigDecimal(getUnitPrice()));
	}

}
