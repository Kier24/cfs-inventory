package com.cfs.inventory.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import com.cfs.inventory.converter.LocalDateAttributeConverter;

@Entity
public class Sale {

	@Id
	@GeneratedValue
	private Long id;
	@OneToMany(mappedBy = "sale", orphanRemoval = true, cascade = CascadeType.ALL)
	@MapKey(name = "product")
	private Map<Product, SalesLineItem> items;
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate orderDate;
	private String customerName;
	@Embedded
	private Delivery delivery;
	@Enumerated(EnumType.STRING)
	private Status status;

	public Sale(String customerName, Delivery delivery) {
		
		if (customerName == null || customerName.equals("")) {
			throw new IllegalArgumentException("Customer name cannot be empty or null");
		}
		if (delivery == null) {
			throw new IllegalArgumentException("Delivery cannot be null");
		}
		
		this.items = new HashMap<>();
		this.orderDate = LocalDate.now();
		this.customerName = customerName;
		this.delivery = delivery;
		this.status=Status.CREATED;
	}

	public Long getId() {
		return id;
	}

	public String getCustomerName() {
		return customerName;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setItemQuantity(Product product, int quantity) {
		if (product == null) {
			throw new IllegalArgumentException("Product cannot be null");
		}
		if (product.getId() == null) {
			throw new IllegalArgumentException("Product ID cannot be null");
		}
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity cannot be negative");
		}
		if (quantity > product.getQuantity()) {
			throw new IllegalArgumentException("Quantity cannot be greater than product current stock");
		}
		if (quantity == 0) {
			removeItem(product);
		} else {
			SalesLineItem item = items.get(product);
			if (item != null) {
				quantity = item.getQuantity() + quantity;
				item.setQuantity(quantity);
			} else {
				items.put(product, new SalesLineItem(this, product, quantity));
			}
		}
	}

	public void removeItem(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("Product cannot be null");
		}
		items.remove(product);
	}
	
	public void returnItem(Product product,int quantity) {
		if (product == null) {
			throw new IllegalArgumentException("Product cannot be null");
		}
		if (product.getId() == null) {
			throw new IllegalArgumentException("Product ID cannot be null");
		}
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity cannot be negative");
		}
		if(items.get(product)==null) {
			throw new IllegalStateException("Product to return does not exist from order");
		}else {
			SalesLineItem item = items.get(product);
			if(item.getQuantity()<=quantity) {
				removeItem(product);
			}else {
				item.setQuantity(item.getQuantity()-quantity);
			}
		}
		
		
	}

	public void clear() {
		items.clear();
	}

	public List<SalesLineItem> getItems() {
		return new ArrayList<SalesLineItem>(items.values());
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	protected Sale() {

	}
}
