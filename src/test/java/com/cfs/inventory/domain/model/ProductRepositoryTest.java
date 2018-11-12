package com.cfs.inventory.domain.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cfs.inventory.config.SystemTestConfig;
import com.cfs.inventory.service.SaleApplicationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SystemTestConfig.class)
@Transactional
public class ProductRepositoryTest {

	@Autowired
	private SaleApplicationService saleApplication;
	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void getAllProductsAndTotalNumberDeliveredByDate() {
//		Product product = new Product("Ketchup","GALLON",12,MaterialType.FINISHED_GOODS,50,10);
//		entityManager.persist(product);
//		Product oysterSauce = new Product("Oyster Sauce","HALF GALLON",12,MaterialType.FINISHED_GOODS,60,20);
//		entityManager.persist(oysterSauce);
//		saleApplication.enterItem(product.getId(),10);
//		saleApplication.enterItem(product.getId(), 5);
//		saleApplication.enterItem(oysterSauce.getId(), 20);
//		Delivery delivery = new Delivery("Calaca,Batangas",LocalDate.of(2018, 7, 17),"Kim");
//		saleApplication.createNewOrder("Kier Tenorio",delivery);
//		
//		List<ProductDto> productDtoList = productRepository.getDailyProductOrdered(LocalDate.now());
//		assertEquals(2,productDtoList.size());
	}
}
