package com.cfs.inventory.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private int quantity;
	private int criticalLevel;
	@Enumerated(EnumType.STRING)
	private ProductCategory category;

	public Product(String name, String description,int quantity,int criticalLevel,ProductCategory category) {
		this.name = name;
		this.description = description;
		this.quantity=quantity;
		this.criticalLevel=criticalLevel;
		this.category=category;
	}
	
	public void deductStock(int quantity){
		if(quantity>this.quantity){
			throw new IllegalStateException("Quantity to be deduct cannot be greater than current stock");
		}
		this.quantity-=quantity;
	}
	public Product(){
		
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
