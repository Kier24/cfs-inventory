package com.cfs.inventory.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String containerType;
	@Enumerated(EnumType.STRING)
	private ProductCategory category;
	private int quantity;
	private int criticalLevel;
	private int quantityPerBox;

	public Product(String name, String containerType, int quantityPerBox, ProductCategory category) {
		this(name, containerType, quantityPerBox, category, 1, 0);
	}

	public Product(String name, String containerType, int quantityPerBox, ProductCategory category, int quantity,
			int criticalLevel) {
		this.name = name;
		this.containerType = containerType;
		this.quantityPerBox = quantityPerBox;
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
		result = prime * result + ((category == null) ? 0 : category.hashCode());
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
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (category != other.category)
			return false;
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
