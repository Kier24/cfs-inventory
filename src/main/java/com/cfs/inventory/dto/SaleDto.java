package com.cfs.inventory.dto;

import java.util.List;

import com.cfs.inventory.domain.model.Delivery;
import com.cfs.inventory.domain.model.SalesLineItem;

public class SaleDto {

	private String customerName;
	private Delivery deliveryDetails;
	private List<SalesLineItem> items;

	public SaleDto(String customerName, Delivery deliveryDetails, List<SalesLineItem> items) {
		this.customerName = customerName;
		this.deliveryDetails = deliveryDetails;
		this.items = items;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Delivery getDeliveryDetails() {
		return deliveryDetails;
	}

	public List<SalesLineItem> getItems() {
		return items;
	}
	
	

}
