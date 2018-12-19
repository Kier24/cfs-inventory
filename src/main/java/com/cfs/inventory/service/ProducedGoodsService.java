package com.cfs.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfs.inventory.model.ContainerType;
import com.cfs.inventory.model.ContainerTypeRepository;
import com.cfs.inventory.model.Ingredient;
import com.cfs.inventory.model.ProducedGood;
import com.cfs.inventory.model.ProducedGoodRepository;
import com.cfs.inventory.model.Product;
import com.cfs.inventory.model.ProductRepository;
import com.cfs.inventory.model.RawMaterial;
import com.cfs.inventory.model.RawMaterialRepository;

@Service
public class ProducedGoodsService {

	
	@Autowired
	ProducedGoodRepository producedGoodRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private RawMaterialRepository rawMaterialRepository;
	@Autowired
	private ContainerTypeRepository containerTypeRepository;
	
	@Transactional
	public void saveAll(List<ProducedGood> producedGoodList) {
		for(ProducedGood good :producedGoodList) {
			Product product =productRepository.getOne(good.getProduct().getId());
			ContainerType container = containerTypeRepository.getOne(good.getContainerType().getId());
			for(Ingredient ingredient:good.getIngredientList()) {
				RawMaterial material = rawMaterialRepository.getOne(ingredient.getRawMaterial().getId());
				material.deductStock(ingredient.getQuantity());
			}
			good.setProduct(product);
			good.setContainerType(container);
		}
		producedGoodRepository.saveAll(producedGoodList);
	}
}
