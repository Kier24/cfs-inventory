package com.cfs.inventory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProducedGoodDto {
	private Long id;
	private String name;
	private int quantity;
	private int criticalLevel;
	private String containerType;
	private int quantityPerBox;

}
