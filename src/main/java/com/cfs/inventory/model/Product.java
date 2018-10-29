package com.cfs.inventory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Getter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private int quantity;
	private int criticalLevel;

	public Product(String name, String description, int quantity, int criticalLevel) {
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.criticalLevel = criticalLevel;
	}

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
		return true;
	}

}
