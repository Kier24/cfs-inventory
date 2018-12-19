package com.cfs.inventory.service;

import java.math.BigDecimal;

import com.cfs.inventory.model.ProducedGood;

import lombok.Getter;

@Getter
public class CartItem {

	private ProducedGood producedGood;
	private String name;
	private String containerType;
	private int quantity;
	private BigDecimal subTotal= BigDecimal.ZERO;

	public CartItem(ProducedGood producedGood, int quantity) {
		this.producedGood = producedGood;
		this.name=producedGood.getProductName();
		this.containerType=producedGood.getContainerType().getName();
		this.quantity = quantity;
		computeSubTotal(producedGood);
	}
	
	private void computeSubTotal(ProducedGood producedGood) {
		subTotal=producedGood.getPrice().multiply(BigDecimal.valueOf(quantity));
	}
	
}
