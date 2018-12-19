package com.cfs.inventory.dto;

import lombok.Getter;

@Getter
public class ProductReportDto {

	private String name;
	private int stock;
	private long delivered;

	public ProductReportDto(String name, int stock, long delivered) {
		this.name = name;
		this.stock = stock;
		this.delivered = delivered;
	}
}
