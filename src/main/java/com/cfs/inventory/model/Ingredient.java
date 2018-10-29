package com.cfs.inventory.model;

import javax.persistence.Entity;

@Entity
public class Ingredient extends Product {

	private String metric;

	public Ingredient(String metric) {
		this.metric = metric;
	}

	public String getMetric() {
		return metric;
	}
}
