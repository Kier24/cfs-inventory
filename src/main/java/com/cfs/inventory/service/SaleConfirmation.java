package com.cfs.inventory.service;

import java.util.List;

import com.cfs.inventory.model.SalesLineItem;

public class SaleConfirmation {

	private List<SalesLineItem> itemList;
	private String customerName;

	public List<SalesLineItem> getItemList() {
		return itemList;
	}

	public String getCustomerName() {
		return customerName;
	}

	SaleConfirmation(List<SalesLineItem> itemList, String customerName) {
		this.itemList = itemList;
		this.customerName = customerName;
	}
}
