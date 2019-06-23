package com.cfs.inventory.service;

import java.util.List;

import com.cfs.inventory.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProducedGoodsService {

	
	@Autowired
	ProducedGoodRepository producedGoodRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private RawMaterialRepository rawMaterialRepository;
	@Autowired
	private ContainerRepository containerRepository;
	
	@Transactional
	public void saveAll(List<Product> products) {
		ProducedGood producedGood = ProducedGood.builder().build();
		producedGoodRepository.save(producedGood);
	}
}
