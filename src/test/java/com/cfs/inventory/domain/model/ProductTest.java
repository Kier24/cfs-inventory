package com.cfs.inventory.domain.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cfs.inventory.model.Product;
import com.cfs.inventory.model.MaterialType;

public class ProductTest {

	@Test
	public void deductProductStock() {
		Product product = new Product("Ketchup", "Banana Ketchup", 12, MaterialType.FINISHED_GOODS);
		product.deductStock(4);
		assertEquals(6, product.getQuantity());
	}

	@Test(expected = IllegalStateException.class)
	public void deductProductStockHigherThanCurrentStock() {
		Product product = new Product("Ketchup", "Banana Ketchup", 12, MaterialType.FINISHED_GOODS);
		product.deductStock(15);

	}

}
