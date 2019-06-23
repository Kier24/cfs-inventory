package com.cfs.inventory.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cfs.inventory.model.Product;
import com.cfs.inventory.model.ProductRepository;

import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
public class ProducedGoodsContainer {

	private List<ProducedGoodDto> producedGoodDtoList = new ArrayList<>();

	@Autowired
	private ProductRepository productRepository;

	public void add(ProducedGoodDto producedGoodDto) {
		Product product = productRepository.getOne(producedGoodDto.getProductId());
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		producedGoodDto.setProduct(productDto);
		producedGoodDtoList.add(producedGoodDto);
	}

	public void clear() {
		producedGoodDtoList.clear();
	}

}
