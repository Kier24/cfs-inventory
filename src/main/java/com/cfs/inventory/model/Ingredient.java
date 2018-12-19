package com.cfs.inventory.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Embeddable
@Getter
public class Ingredient {
	
	@ManyToOne
	private RawMaterial rawMaterial;
	private int quantity;

	public Ingredient(RawMaterial rawMaterial, int quantity) {
		this.rawMaterial = rawMaterial;
		this.quantity = quantity;
	}
	
	protected Ingredient() {
		
	}
}
