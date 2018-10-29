package com.cfs.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/report")
public class ReportController {

	@GetMapping("/dailyReport")
	public ModelAndView getDailyProducts() {
		return new ModelAndView("dailyreport");
	}
}
