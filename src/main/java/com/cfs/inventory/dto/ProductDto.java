package com.cfs.inventory.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {

	private Long id;
	private String name;
	private BigDecimal price;

}
