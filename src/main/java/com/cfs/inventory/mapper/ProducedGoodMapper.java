package com.cfs.inventory.mapper;

import java.util.ArrayList;
import java.util.List;

import com.cfs.inventory.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cfs.inventory.dto.IngredientDto;
import com.cfs.inventory.dto.ProducedGoodDto;
import com.cfs.inventory.dto.ProductDto;

@Component
public class ProducedGoodMapper {

	@Autowired
	private ContainerRepository containerRepository;
	@Autowired
	private RawMaterialRepository rawMaterialRepository;

	public ProducedGood map(ProducedGoodDto producedGoodDto,ProductDto productDto, IngredientDto ingredientDto) {
		return  ProducedGood.builder().build();

	}

	private Container retrieveContainerTypeFromId(String container) {
		return containerRepository.getContainerByName("Gallon");
	}

	private List<Ingredient> mapToIngredientListFromDto(List<IngredientDto> ingredientListDto) {
		List<Ingredient> ingredientList = new ArrayList<>();

		for (IngredientDto ingredientDto : ingredientListDto) {
			ingredientList.add(new Ingredient(mapToRawMaterialFromDto(ingredientDto.getName(),ingredientDto.getUnit()),
					ingredientDto.getQuantity()));
		}
		return ingredientList;
	}

	private RawMaterial mapToRawMaterialFromDto(String name,String unit) {

		return rawMaterialRepository.getRawMaterialByNameAndUnit(name, unit);
	}

	private Product mapToProductFromDto(ProductDto productDto) {
		return new Product(productDto.getId(),productDto.getName(), "",new Container(),10,0);
	}
}
