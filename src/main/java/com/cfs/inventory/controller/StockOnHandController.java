package com.cfs.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.inventory.model.ProducedGoodRepository;


@Controller
@RequestMapping(value = "/stockOnHand")
public class StockOnHandController {

	@Autowired
	private ProducedGoodRepository producedGoodRepository;
	
	@GetMapping("")
	public ModelAndView viewStockOnHand() {
		ModelAndView mav = new ModelAndView("stockonhand");
		mav.addObject("producedGoods", producedGoodRepository.findAll());
		return mav;
	}
}
