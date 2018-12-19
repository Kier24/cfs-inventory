package com.cfs.inventory.controller;

import static com.cfs.inventory.model.MaterialType.LIQUID;
import static com.cfs.inventory.model.MaterialType.SOLID;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.inventory.dto.ProducedGoodDto;
import com.cfs.inventory.dto.RawMaterialDto;
import com.cfs.inventory.model.ContainerType;
import com.cfs.inventory.model.ContainerTypeRepository;
import com.cfs.inventory.model.ProducedGood;
import com.cfs.inventory.model.ProducedGoodRepository;
import com.cfs.inventory.model.RawMaterial;
import com.cfs.inventory.model.RawMaterialRepository;
import com.github.dozermapper.core.Mapper;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController {

	@Autowired
	private RawMaterialRepository rawMaterialRepository;
	@Autowired
	private ProducedGoodRepository producedGoodRepository;
	
	@Autowired
	private Mapper modelMapper;

	@GetMapping(value = "/rawMaterials")
	public String viewRawMaterials() {
		return "rawmaterial";
	}
	@GetMapping(value = "/rawMaterials/liquid")
	public ModelAndView getLiquidRawMaterials(RawMaterialDto rawMaterialDto) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("rawprod");
		modelView.addObject("title", "Liquid Materials");
		modelView.addObject("materialList", rawMaterialRepository.getRawMaterialByMaterialType(LIQUID));
		modelView.addObject("action", "/inventory/rawMaterials/liquid/add");
		return modelView;
	}

	@GetMapping(value = "/rawMaterials/solid")
	public ModelAndView getSolidRawMaterials(RawMaterialDto rawMaterialDto) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("rawprod");
		modelView.addObject("title", "Solid Materials");
		modelView.addObject("materialList", rawMaterialRepository.getRawMaterialByMaterialType(SOLID));
		modelView.addObject("action", "/inventory/rawMaterials/solid/add");
		return modelView;
	}
	
	@GetMapping(value="/producedGoods/{productId}")
	@ResponseBody
	public ProducedGood getProducedGood(@PathVariable("productId")Long productId) {
		return producedGoodRepository.getOne(productId);
	}
	
	@GetMapping(value="/producedGoods/dateCreated/{date}")
	@ResponseBody
	public List<ProducedGood> getProducedGoodsByDateCreated(@PathVariable("date") @DateTimeFormat(iso=ISO.DATE) LocalDate date){
		System.out.println(date.toString());
		return producedGoodRepository.getProducedGoodsByDateCreated(date);
	}


	@PostMapping(value = "/rawMaterials/solid/add")
	public String saveSolidRawMaterial(@Valid @ModelAttribute("rawMaterial") RawMaterialDto rawMaterialDto) {
		rawMaterialDto.setMaterialType(SOLID);
		RawMaterial newRawMaterial = modelMapper.map(rawMaterialDto, RawMaterial.class);

		rawMaterialRepository.save(newRawMaterial);

		return "redirect:/inventory/rawMaterials/solid";
	}

	@PostMapping(value = "/rawMaterials/liquid/add")
	public String saveLiuqidRawMaterial(@Valid @ModelAttribute("rawMaterial") RawMaterialDto rawMaterialDto) {
		rawMaterialDto.setMaterialType(LIQUID);
		RawMaterial newRawMaterial = modelMapper.map(rawMaterialDto, RawMaterial.class);
		rawMaterialRepository.save(newRawMaterial);

		return "redirect:/inventory/rawMaterials/liquid";
	}

	@PostMapping(value = "/rawMaterials/liquid/delete")
	public String deleteLiquidRawMaterial(@RequestParam(name = "deleteId") Long materialId) {
		rawMaterialRepository.deleteById(materialId);
		return "redirect:/inventory/rawMaterials/liquid";
	}

	@PostMapping(value = "/rawMaterials/solid/delete")
	public String deleteSolidRawMaterial(@RequestParam(name = "deleteId") Long materialId) {
		rawMaterialRepository.deleteById(materialId);
		return "redirect:/inventory/rawMaterials/solid";
	}
	
	@GetMapping(value="/returnedGoods")
	public String getReturnedGoods() {
		return "returnedGoods";
	}

}
