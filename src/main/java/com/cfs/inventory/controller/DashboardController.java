package com.cfs.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.inventory.model.SaleRepository;

@Controller
public class DashboardController {
	
	@Autowired
	private SaleRepository saleRepository;
	
	@GetMapping("/")
	public ModelAndView showIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("totalOrderedItems", saleRepository.getTotalOrderedItems());
		mav.addObject("totalDeliveredItems",saleRepository.getTotalDeliveredItems());
		return mav;
	}
}
