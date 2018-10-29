package com.cfs.inventory.service;

import org.springframework.stereotype.Service;

import com.cfs.inventory.domain.model.Product;
import com.cfs.inventory.domain.model.Sale;

@Service
public class ProductReturnService {

	public void returnItem(Sale sale,Product product,int quantity) {
		sale.returnItem(product, quantity);
	}
}
