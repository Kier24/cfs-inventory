package com.cfs.inventory.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.inventory.model.ProducedGoodRepository;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ProducedGoodRepository producedGoodRepository;

	@GetMapping("/dailyReport")
	public ModelAndView getDailyProducts(@RequestParam(required = false, name = "orderDate") Date orderDate) {
		ModelAndView mav = new ModelAndView("dailyreport");

		if (orderDate != null) {
			mav.addObject("productList", producedGoodRepository.getDailyProductOrdered(orderDate.toLocalDate()));
		}

		return mav;
	}

	@GetMapping("/weeklyReport")
	public ModelAndView getWeeklyProducts(@RequestParam(required = false, name = "orderDate") Date orderDate) {
		ModelAndView mav = new ModelAndView("weeklyreport");
	
		if (orderDate != null) {
			LocalDate localOrderDate = orderDate.toLocalDate();
			TemporalField woy = WeekFields.ISO.weekOfMonth();
			int weekNumber = localOrderDate.get(woy);
			mav.addObject("productList", producedGoodRepository.getWeeklyProductOrdered(weekNumber));
		}

		return mav;
	}

	@GetMapping("/monthlyReport")
	public ModelAndView getMonthlyProducts(@RequestParam(required = false, name = "orderDate") Date orderDate) {
		ModelAndView mav = new ModelAndView("monthlyreport");

		if (orderDate != null) {
			LocalDate localOrderDate = orderDate.toLocalDate();
			int month = localOrderDate.getMonthValue();
			mav.addObject("productList", producedGoodRepository.getMonthlyProductOrdered(month));
		}

		return mav;
	}
}
