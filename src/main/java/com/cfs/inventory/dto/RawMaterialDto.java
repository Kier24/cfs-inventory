package com.cfs.inventory.dto;

import com.cfs.inventory.model.MaterialType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RawMaterialDto {
	private long id;
	private String name;
	private int quantity;
	private int criticalLevel;
	private MaterialType category;
}
