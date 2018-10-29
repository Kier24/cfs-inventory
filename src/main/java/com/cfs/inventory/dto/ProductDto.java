package com.cfs.inventory.dto;

public class ProductDto {

	private String name;
	private int currentStock;
	private long totalNumberOrdered;

	public ProductDto(String name, int currentStock, long totalNumberOrdered) {
		this.name = name;
		this.currentStock = currentStock;
		this.totalNumberOrdered = totalNumberOrdered;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

	public void setTotalNumberOrdered(int totalNumberOrdered) {
		this.totalNumberOrdered = totalNumberOrdered;
	}

	public String getName() {
		return name;
	}

	public int getCurrentStock() {
		return currentStock;
	}

	public long getTotalNumberOrdered() {
		return totalNumberOrdered;
	}

}
