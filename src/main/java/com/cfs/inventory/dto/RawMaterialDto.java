package com.cfs.inventory.dto;

import com.cfs.inventory.model.MaterialType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RawMaterialDto {
	private Long id;
	private String name;
	private int quantity;
	private String unit;
	private int criticalLevel;
	private MaterialType materialType;
}
