package com.cfs.inventory.domain.model;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import com.cfs.inventory.converter.LocalDateAttributeConverter;

@Entity
public class Delivery {

	@Id
	@GeneratedValue
	private Long id;
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate deliveryDate;
	private String deliveryAddress;
	@Enumerated(EnumType.STRING)
	private Status status;

	public Delivery(String deliveryAddress,LocalDate deliveryDate) {
		this.status = Status.PENDING;
		this.deliveryAddress=deliveryAddress;
		this.deliveryDate=deliveryDate;
	}
	
	public Delivery(){
		this.status=Status.PENDING;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
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
		Delivery other = (Delivery) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
