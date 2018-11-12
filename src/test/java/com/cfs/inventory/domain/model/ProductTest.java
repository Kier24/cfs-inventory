package com.cfs.inventory.domain.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cfs.inventory.model.MaterialType;

public class ProductTest {

	@Test
	public void deductProductStock() {

	}

	@Test(expected = IllegalStateException.class)
	public void deductProductStockHigherThanCurrentStock() {


	}

}
