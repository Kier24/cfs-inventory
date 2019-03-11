package com.cfs.inventory.model;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
@Getter
public class Ingredient {
	
	@OneToMany
	private RawMaterial rawMaterial;
	private int quantity;

	public Ingredient(RawMaterial rawMaterial, int quantity) {
		this.rawMaterial = rawMaterial;
		this.quantity = quantity;
	}
	
	protected Ingredient() {
		
	}
}
