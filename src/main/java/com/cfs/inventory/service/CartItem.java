package com.cfs.inventory.service;

import com.cfs.inventory.model.Product;

public class CartItem {

	private Product product;
	private String name;
	private String containerType;
	private int quantity;

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.name=product.getName();
		this.containerType=product.getContainerType();
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public String getName() {
		return name;
	}

	public String getContainerType() {
		return containerType;
	}

	public int getQuantity() {
		return quantity;
	}
}
