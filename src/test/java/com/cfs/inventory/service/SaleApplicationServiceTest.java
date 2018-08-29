package com.cfs.inventory.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cfs.inventory.config.SystemTestConfig;
import com.cfs.inventory.domain.model.Delivery;
import com.cfs.inventory.domain.model.Product;
import com.cfs.inventory.domain.model.ProductCategory;
import com.cfs.inventory.domain.model.ProductRepository;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SystemTestConfig.class)
public class SaleApplicationServiceTest {
	
	@Autowired
	private SaleApplicationService saleApplication;
	@Autowired 
	private ProductRepository productRepository;
	@PersistenceContext
	private EntityManager entityManager;
	
	@Test
	public void successfulOrder() {
		Product product = new Product("Ketchup","Banana Ketchup",50,5,ProductCategory.FINISHED_GOODS);
		entityManager.persist(product);
		Product oysterSauce = new Product("Oyster Sauce","Oyster Sauce",50,5,ProductCategory.FINISHED_GOODS);
		entityManager.persist(oysterSauce);
		saleApplication.enterItem(product.getId(),10);
		saleApplication.enterItem(product.getId(), 5);
		saleApplication.enterItem(oysterSauce.getId(), 20);
		Delivery delivery = new Delivery();
		delivery.setDeliveryDate(LocalDate.of(2018, 7, 17));
		delivery.setDeliveryAddress("Calaca,Batangas");
		SaleConfirmation saleConfirmation =saleApplication.createNewOrder("Kier Tenorio",delivery);
		
		assertEquals(2,saleConfirmation.getItemList().size());
		
		Product newProduct=productRepository.findOne(product.getId());
		assertEquals(newProduct.getQuantity(),5);
	}
}
