package com.cfs.inventory.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.cfs.inventory.model.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProducedGoodDto {

	private Long productId;
	private List<IngredientDto> ingredientList;
	private ProductDto product;
	private String container;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateCreated;
	private int quantity;
	private int criticalLevel;
	private String encoder;

}
