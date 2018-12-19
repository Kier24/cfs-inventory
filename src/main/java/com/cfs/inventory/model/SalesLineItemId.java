package com.cfs.inventory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
class SalesLineItemId implements Serializable{
	
	@Column(name = "sale_id", updatable = false, nullable = false)
	private Long saleId;
	@Column(name = "producedGood_id", updatable = false, nullable = false)
	private Long producedGoodId;

	SalesLineItemId(Sale sale, ProducedGood producedGood) {
		this.saleId = sale.getId();
		this.producedGoodId = producedGood.getId();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((producedGoodId == null) ? 0 : producedGoodId.hashCode());
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
		if (producedGoodId == null) {
			if (other.producedGoodId != null)
				return false;
		} else if (!producedGoodId.equals(other.producedGoodId))
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
