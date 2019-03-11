package com.cfs.inventory.model;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class SalesLineItem {

	@EmbeddedId
	private SalesLineItemId id;

	@ManyToOne
	@MapsId("saleId")
	private Sale sale;

	@ManyToOne
	@MapsId("productId")
	private Product product;

	@Enumerated(EnumType.STRING)
	private OrderType orderType;

	private int quantity;
	private BigDecimal price;

	SalesLineItem(Sale sale, Product product, int quantity) {
		
		if (sale == null) {
			throw new IllegalArgumentException("Sale cannot be null");
		}
		if (this.product == null) {
			throw new IllegalArgumentException("Product cannot be null");
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than zero");
		}
		
		this.id = new SalesLineItemId(sale, product);
		this.sale = sale;
		this.product = product;
		this.quantity = quantity;
		this.price=computePrice(this.product.getPrice(),quantity);
	}
	
	private BigDecimal computePrice(BigDecimal productPrice, int quantity) {
		return productPrice.multiply(BigDecimal.valueOf(quantity));
	}
	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	protected SalesLineItem() {

	}

}
