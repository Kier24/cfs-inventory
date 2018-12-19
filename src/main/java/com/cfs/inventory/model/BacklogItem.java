package com.cfs.inventory.model;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.cfs.inventory.converter.LocalDateAttributeConverter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BacklogItem {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@Convert(converter = LocalDateAttributeConverter.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private int quantity;

}
