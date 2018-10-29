package com.cfs.inventory.service;

import com.cfs.inventory.model.Product;

public class CartItem {

	private Product product;
	private String name;
	private String description;
	private int quantity;

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.name=product.getName();
		this.description=product.getDescription();
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getQuantity() {
		return quantity;
	}
}
