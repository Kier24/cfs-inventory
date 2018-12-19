package com.cfs.inventory.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Component
public class IngredientContainer {
	List<IngredientDto> ingredientList = new ArrayList<>();
	
	public void add(IngredientDto ingredientDto) {
		ingredientList.add(ingredientDto);
	}
	
	public List<IngredientDto> getIngredientList(){
		return new ArrayList<>(ingredientList);
	}
	public void clear() {
		ingredientList.clear();
	}
}
