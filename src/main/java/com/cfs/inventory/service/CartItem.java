package com.cfs.inventory.service;

import com.cfs.inventory.model.ProducedGood;

import lombok.Getter;

@Getter
public class CartItem {

	private ProducedGood producedGood;
	private String name;
	private String containerType;
	private int quantity;

	public CartItem(ProducedGood producedGood, int quantity) {
		this.producedGood = producedGood;
		this.name=producedGood.getName();
		this.containerType=producedGood.getContainerType().getName();
		this.quantity = quantity;
	}

	
}
