package com.cfs.inventory.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Builder;
import lombok.AllArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	@OneToOne
	private Container container;
	private int quantity;
	private int criticalLevel;

	public void deductStock(int quantity) {
		if (quantity > this.quantity) {
			throw new IllegalStateException("Quantity to be deduct cannot be greater than current stock");
		}
		this.quantity -= quantity;
	}

	protected Product() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
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
