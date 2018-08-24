package com.cfs.inventory.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class SalesLineItemId implements Serializable{
	
	@Column(name = "sale_id", updatable = false, nullable = false)
	private Long saleId;
	@Column(name = "product_id", updatable = false, nullable = false)
	private Long productId;

	SalesLineItemId(Sale sale, Product product) {
		this.saleId = sale.getId();
		this.productId = product.getId();
	}

	public Long getSaleId() {
		return saleId;
	}

	public Long getProductId() {
		return productId;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((saleId == null) ? 0 : saleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesLineItemId other = (SalesLineItemId) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (saleId == null) {
			if (other.saleId != null)
				return false;
		} else if (!saleId.equals(other.saleId))
			return false;
		return true;
	}

	protected SalesLineItemId(){
		
	}
	
	

}
