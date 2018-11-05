package com.cfs.inventory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProducedGood {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private int quantity;
	private int criticalLevel;
	private String containerType;
	private int quantityPerBox;

	public void deductStock(int quantity) {
		if (quantity > this.quantity) {
			throw new IllegalStateException("Quantity to be deduct cannot be greater than current stock");
		}
		this.quantity -= quantity;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((containerType == null) ? 0 : containerType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProducedGood))
			return false;
		ProducedGood other = (ProducedGood) obj;
		if (containerType == null) {
			if (other.containerType != null)
				return false;
		} else if (!containerType.equals(other.containerType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
