package com.cfs.inventory.model;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

import com.cfs.inventory.converter.LocalDateAttributeConverter;

@Embeddable
public class Delivery {

	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate deliveryDate;
	private String deliveryAddress;
	private String deliveredBy;

	public Delivery(String deliveryAddress, LocalDate deliveryDate, String deliveredBy) {
		this.deliveryAddress = deliveryAddress;
		this.deliveryDate = deliveryDate;
		this.deliveredBy = deliveredBy;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	
	public String getDeliveredBy() {
		return deliveredBy;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	protected Delivery() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deliveryAddress == null) ? 0 : deliveryAddress.hashCode());
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
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
		Delivery other = (Delivery) obj;
		if (deliveryAddress == null) {
			if (other.deliveryAddress != null)
				return false;
		} else if (!deliveryAddress.equals(other.deliveryAddress))
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		return true;
	}

}
