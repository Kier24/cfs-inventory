package com.cfs.inventory.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.cfs.inventory.converter.LocalDateAttributeConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProducedGood {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	private Product product;
	@ElementCollection
	private List<Ingredient> ingredientList;
	@ManyToOne(cascade = CascadeType.ALL)
	private ContainerType containerType;
	private int quantity;
	private int criticalLevel;
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate dateCreated;
	private String encoder;

	

	public void deductStock(int quantity) {
		if (quantity > this.quantity) {
			throw new IllegalStateException("Quantity to be deduct cannot be greater than current stock");
		}
		this.quantity -= quantity;
	}
	
	public BigDecimal getPrice() {
		return product.getPrice();
	}
	
	public String getProductName() {
		return product.getName();
	}
	
	protected ProducedGood() {
		
	}

}
