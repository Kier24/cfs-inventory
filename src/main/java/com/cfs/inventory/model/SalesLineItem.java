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
	@MapsId("producedGoodId")
	private ProducedGood producedGood;

	@Enumerated(EnumType.STRING)
	private OrderType orderType;

	private int quantity;
	private BigDecimal price;

	SalesLineItem(Sale sale, ProducedGood producedGood, int quantity) {
		
		if (sale == null) {
			throw new IllegalArgumentException("Sale cannot be null");
		}
		if (producedGood == null) {
			throw new IllegalArgumentException("Product cannot be null");
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than zero");
		}
		
		this.id = new SalesLineItemId(sale, producedGood);
		this.sale = sale;
		this.producedGood = producedGood;
		this.quantity = quantity;
		this.price=computePrice(producedGood.getPrice(),quantity);
	}
	
	private BigDecimal computePrice(BigDecimal productPrice, int quantity) {
		return productPrice.multiply(BigDecimal.valueOf(quantity));
	}
	public ProducedGood getProducedGood() {
		return producedGood;
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
