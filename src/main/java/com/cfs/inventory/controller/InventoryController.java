package com.cfs.inventory.controller;

import static com.cfs.inventory.model.MaterialType.INGREDIENT_LIQUID;
import static com.cfs.inventory.model.MaterialType.INGREDIENT_SOLID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.inventory.dto.ProducedGoodDto;
import com.cfs.inventory.dto.RawMaterialDto;
import com.cfs.inventory.model.ProducedGoodRepository;
import com.cfs.inventory.model.Product;
import com.cfs.inventory.model.RawMaterial;
import com.cfs.inventory.model.RawMaterialRepository;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController {

	@Autowired
	private RawMaterialRepository rawMaterialRepository;
	@Autowired
	private ProducedGoodRepository producedGoodRepository;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/rawMaterials/liquid")
	public ModelAndView getLiquidRawMaterials(RawMaterialDto rawMaterialDto) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("rawprod");
		modelView.addObject("materialList", rawMaterialRepository.getRawMaterialByMaterialType(INGREDIENT_LIQUID));
		return modelView;
	}

	@GetMapping(value = "/rawMaterials/solid")
	public ModelAndView getSolidRawMaterials(RawMaterialDto rawMaterialDto) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("rawprod");
		modelView.addObject("materialList", rawMaterialRepository.getRawMaterialByMaterialType(INGREDIENT_SOLID));
		return modelView;
	}

	@GetMapping(value = "/finishedGoods")
	public ModelAndView getFinishedGoods(ProducedGoodDto producedGoodDto) {
		ModelAndView modelView = new ModelAndView();

		modelView.setViewName("finishedgood");
		return modelView;
	}

	@GetMapping(value = "/producedGoods")
	public ModelAndView getProducedGoods(ProducedGoodDto producedGoodDto) {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("producedGoods", producedGoodRepository.findAll());
		modelView.setViewName("producedgoods");
		return modelView;
	}

	@PostMapping(value = "/finishedGoods/add")
	public String saveFinishedGoods(@Valid @ModelAttribute("product") (ProducedGoodDto producedGoodDto) {

		return "redirect:/inventory/finishedGoods";
	}

	@PostMapping(value = "/finishedGoods/delete")
	public String deleteFinishedGood(@RequestParam(name = "deleteId") Long productId) {

		return "redirect:/inventory/finishedGoods";
	}

	@PostMapping(value = "/rawMaterials/solid/add")
	public String saveSolidRawMaterial(@Valid @ModelAttribute("rawMaterial") RawMaterialDto rawMaterialDto) {
		rawMaterialDto.setCategory(INGREDIENT_SOLID);
		RawMaterial newRawMaterial = modelMapper.map(rawMaterialDto, RawMaterial.class);

		rawMaterialRepository.save(newRawMaterial);
		
		return "redirect:/inventory/rawMaterials/solid";
	}
	
	@PostMapping(value = "/rawMaterials/liquid/add")
	public String saveLiuqidRawMaterial(@Valid @ModelAttribute("rawMaterial") RawMaterialDto rawMaterialDto) {
		rawMaterialDto.setCategory(INGREDIENT_LIQUID);
		RawMaterial newRawMaterial = modelMapper.map(rawMaterialDto, RawMaterial.class);
		rawMaterialRepository.save(newRawMaterial);
		
		return "redirect:/inventory/rawMaterials/liuqid";
	}	

	@PostMapping(value = "/rawMaterials/delete")
	public String deleteRawMaterial(@RequestParam(name = "deleteId") Long productId) {

		return "redirect:/inventory/rawMaterials";
	}

}
