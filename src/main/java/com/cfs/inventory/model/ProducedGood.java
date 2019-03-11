package com.cfs.inventory.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

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
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL)
	private Product product;
	@ElementCollection
	private List<Ingredient> ingredientList;
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate dateCreated;
	private String encoder;

	public BigDecimal getPrice() {
		return product.getPrice();
	}
	
	public String getProductName() {
		return product.getName();
	}
	
	protected ProducedGood() {
		
	}

}
