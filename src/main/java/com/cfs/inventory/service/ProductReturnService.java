package com.cfs.inventory.service;

import org.springframework.stereotype.Service;

import com.cfs.inventory.model.ProducedGood;
import com.cfs.inventory.model.Sale;

@Service
public class ProductReturnService {

	public void returnItem(Sale sale,ProducedGood product,int quantity) {
		sale.returnItem(product, quantity);
	}
}
