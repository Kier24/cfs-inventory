package com.cfs.inventory.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProducedGoodDto {
	private Long id;
	private String name;
	private int quantity;
	private int criticalLevel;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateCreated;
	private String containerType;

}
