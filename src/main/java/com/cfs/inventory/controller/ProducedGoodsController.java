package com.cfs.inventory.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.inventory.dto.IngredientContainer;
import com.cfs.inventory.dto.IngredientDto;
import com.cfs.inventory.dto.ProducedGoodDto;
import com.cfs.inventory.dto.ProducedGoodsContainer;
import com.cfs.inventory.mapper.ProducedGoodMapper;
import com.cfs.inventory.model.ProducedGood;
import com.cfs.inventory.model.ProducedGoodRepository;
import com.cfs.inventory.model.ProductRepository;
import com.cfs.inventory.model.RawMaterialRepository;
import com.cfs.inventory.service.ProducedGoodsService;

@Controller
@RequestMapping("/producedGoods")
public class ProducedGoodsController {

	@Autowired
	private ProducedGoodsContainer producedGoodscontainer;
	@Autowired
	private IngredientContainer ingredientContainer;
	@Autowired
	private RawMaterialRepository rawMaterialRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProducedGoodRepository producedGoodRepository;
	@Autowired
	private ProducedGoodMapper mapper;
	@Autowired
	private ProducedGoodsService produceGoodsService;

	@GetMapping("")
	public ModelAndView getProducedGoods() {
		ModelAndView mav = new ModelAndView("producedGoods");
		mav.addObject("producedGoodDetailList", producedGoodRepository.findAll());
		return mav;
	}

	@GetMapping("/product")
	public ModelAndView addProducedGoods(ProducedGoodDto producedGoodDto) {
		ModelAndView mav = new ModelAndView("addProducedGoods");
		mav.addObject("producedGoodList", producedGoodscontainer.getProducedGoodDtoList());
		return mav;
	}

	@PostMapping("/product/add")
	public String addToProductContainer(ProducedGoodDto producedGoodDto) {
		producedGoodDto.setIngredientList(ingredientContainer.getIngredientList());
		producedGoodscontainer.add(producedGoodDto);
		ingredientContainer.clear();
		return "redirect:/producedGoods/product/";
	}

	@PostMapping("/product/save")
	public String saveAllProducedGoods(String encoder, Date productionDate) {
		List<ProducedGoodDto> producedGoodDtoList = producedGoodscontainer.getProducedGoodDtoList();
		List<ProducedGood> producedGoodList = new ArrayList<>();
		for (ProducedGoodDto producedGoodDto : producedGoodDtoList) {
			producedGoodDto.setEncoder(encoder);
			producedGoodDto.setDateCreated(productionDate.toLocalDate());
			producedGoodList.add(mapper.map(producedGoodDto));
		}
		produceGoodsService.saveAll(producedGoodList);
		producedGoodscontainer.clear();
		return "redirect:/producedGoods/";
	}

	@GetMapping("/product/ingredient")
	public ModelAndView getIngredients(ProducedGoodDto producedGoodDto, IngredientDto ingredientDto) {
		ModelAndView mav = new ModelAndView("producedItem");
		mav.addObject("productList", productRepository.findAll());
		mav.addObject("ingredientList", ingredientContainer.getIngredientList());
		mav.addObject("rawMaterialList", rawMaterialRepository.findAll());
		return mav;
	}

	@PostMapping("/product/ingredient/add")
	public String addIngredient(IngredientDto ingredientDto) {
		ingredientContainer.add(ingredientDto);
		return "redirect:/producedGoods/product/ingredient";
	}

	@GetMapping("/ingredient/unit/{rawMaterialName}")
	@ResponseBody
	public List<String> getAvailableUnitForRawMaterial(@PathVariable("rawMaterialName") String rawMaterialName) {
		return rawMaterialRepository.getAllAvailableUnitOf(rawMaterialName);
	}

	@GetMapping("/producedGoods/date/{productionDate}/encoder/{encoder}")
	public List<ProducedGood> getProducedGoodByDateAndEncoder(@PathVariable("productionDate") Date productionDate,
			@PathVariable("encoder") String encoder) {
		return producedGoodRepository.getProducedGoodBydateCreatedAndEncoder(productionDate.toLocalDate(), encoder);
	}

}
