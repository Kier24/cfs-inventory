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
public class ProducedGood {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> product;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Ingredient> ingredients;
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate dateCreated;
	private String encoder;

	protected ProducedGood() {
		
	}

}
