package com.cfs.inventory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cfs.inventory.config.SystemTestConfig;
import com.cfs.inventory.model.Product;
import com.cfs.inventory.model.ProductCategory;
import com.cfs.inventory.model.ProductRepository;

@ContextConfiguration(classes = { SystemTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class StockMonitoringAspectTest {

	@Autowired
	ProductRepository productRepository;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Test
	@Transactional	
	public void productStockLevelMonitor() {
		Product product = new Product("Ketchup", "Banana Ketchup", 50, 5, ProductCategory.FINISHED_GOODS);
		entityManager.persist(product);
		product.deductStock(5);
		
	}
}
