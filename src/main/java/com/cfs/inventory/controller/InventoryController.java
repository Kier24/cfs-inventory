package com.cfs.inventory.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.inventory.domain.model.Product;
import com.cfs.inventory.domain.model.ProductCategory;
import com.cfs.inventory.domain.model.ProductRepository;

@Controller
public class InventoryController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping(value = "/rawMaterials")
	public ModelAndView getRawMaterials() {
		ModelAndView modelView = new ModelAndView();

		modelView.addObject("materialList", productRepository.getProductByCategory(ProductCategory.RAW_MATERIALS));
		modelView.setViewName("rawprod");
		modelView.addObject(new Product());
		return modelView;
	}
	
	@GetMapping(value = "/finishedGoods")
	public ModelAndView getFinishedGoods() {
		ModelAndView modelView = new ModelAndView();

		modelView.addObject("materialList", productRepository.getProductByCategory(ProductCategory.FINISHED_GOODS));
		modelView.setViewName("finishedgood");
		modelView.addObject(new Product());
		return modelView;
	}

	@PostMapping(value = "/rawMaterials/add")
	public String saveRawMaterials(@Valid @ModelAttribute("product") Product product) {
		product.setCategory(ProductCategory.RAW_MATERIALS);
		if(product.getName()==null) {
			throw new IllegalStateException("Product name is null.");
		}
		System.out.println("ID "+product.getId());
		productRepository.save(product);
	
		return "redirect:/rawMaterials";
	}

	@PostMapping(value = "/rawMaterials/delete")
	public String deleteRawMaterial(@RequestParam(name="deleteId") Long productId) {

		productRepository.delete(productId);

		return "redirect:/rawMaterials";
	}
	

}
