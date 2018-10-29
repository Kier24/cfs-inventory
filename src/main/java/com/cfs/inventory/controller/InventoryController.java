package com.cfs.inventory.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.inventory.model.Product;
import com.cfs.inventory.model.ProductCategory;
import com.cfs.inventory.model.ProductRepository;

@Controller
@RequestMapping(value="/inventory")
public class InventoryController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping(value = "/rawMaterials")
	public ModelAndView getRawMaterials(Product product) {
		ModelAndView modelView = new ModelAndView();

		modelView.setViewName("rawprod");
		return modelView;
	}

	@GetMapping(value = "/finishedGoods")
	public ModelAndView getFinishedGoods(Product product) {
		ModelAndView modelView = new ModelAndView();

		modelView.setViewName("finishedgood");
		return modelView;
	}
	
	@GetMapping(value = "/producedGoods")
	public String getProducedGoods() {
		return "producedgoods";
	}

	@PostMapping(value = "/finishedGoods/add")
	public String saveFinishedGoods(@Valid @ModelAttribute("product")Product product) {
		productRepository.save(product);
		return "redirect:/inventory/finishedGoods";
	}
	
	@PostMapping(value = "/finishedGoods/delete")
	public String deleteFinishedGood(@RequestParam(name = "deleteId") Long productId) {

		productRepository.deleteById(productId);

		return "redirect:/inventory/finishedGoods";
	}

	@PostMapping(value = "/rawMaterials/add")
	public String saveRawMaterials(@Valid @ModelAttribute("product") Product product) {
		productRepository.save(product);

		return "redirect:/inventory/rawMaterials";
	}

	@PostMapping(value = "/rawMaterials/delete")
	public String deleteRawMaterial(@RequestParam(name = "deleteId") Long productId) {

		productRepository.deleteById(productId);

		return "redirect:/inventory/rawMaterials";
	}

}
