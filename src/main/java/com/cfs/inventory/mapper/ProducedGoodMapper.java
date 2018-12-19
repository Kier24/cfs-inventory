package com.cfs.inventory.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cfs.inventory.dto.IngredientDto;
import com.cfs.inventory.dto.ProducedGoodDto;
import com.cfs.inventory.dto.ProductDto;
import com.cfs.inventory.model.ContainerType;
import com.cfs.inventory.model.ContainerTypeRepository;
import com.cfs.inventory.model.Ingredient;
import com.cfs.inventory.model.ProducedGood;
import com.cfs.inventory.model.Product;
import com.cfs.inventory.model.RawMaterial;
import com.cfs.inventory.model.RawMaterialRepository;

@Component
public class ProducedGoodMapper {

	@Autowired
	private ContainerTypeRepository containerTypeRepository;
	@Autowired
	private RawMaterialRepository rawMaterialRepository;

	public ProducedGood map(ProducedGoodDto producedGoodDto) {

		Product product = mapToProductFromDto(producedGoodDto.getProduct());
		List<Ingredient> ingredientList = mapToIngredientListFromDto(producedGoodDto.getIngredientList());

		return ProducedGood.builder().product(product).ingredientList(ingredientList)
				.containerType(retrieveContainerTypeFromId(producedGoodDto.getContainer()))
				.quantity(producedGoodDto.getQuantity()).criticalLevel(producedGoodDto.getCriticalLevel())
				.encoder(producedGoodDto.getEncoder()).dateCreated(producedGoodDto.getDateCreated()).build();

	}

	private ContainerType retrieveContainerTypeFromId(String container) {
		return containerTypeRepository.getContainerTypeByName("Gallon");
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
		return new Product(productDto.getId(),productDto.getName(), productDto.getPrice());
	}
}
