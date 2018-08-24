package com.cfs.inventory.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.inventory.domain.model.Delivery;
import com.cfs.inventory.domain.model.Sale;
import com.cfs.inventory.domain.model.SaleRepository;

@Controller
public class DeliveryController {

	@Autowired
	private SaleRepository saleRepository;

	@GetMapping(value = "/delivery")
	public ModelAndView getOrders(@RequestParam(required=false) Date startDate, @RequestParam(required=false) Date endDate) {
		List<Sale> saleList = new ArrayList<>();
		
		if(startDate==null && endDate == null) {
			 saleList = saleRepository.findAll();
		}else {
			LocalDate localStartDate=startDate.toLocalDate();
			LocalDate localEndDate=endDate.toLocalDate();
			saleList = saleRepository.findOrdersByDate(localStartDate, localEndDate);
		}
		

		return new ModelAndView("delivery", "orderList", saleList);

	}

	@PostMapping(value = "/deliveryEvents")
	@ResponseBody
	public List<Event> getDeliveryEvents(@RequestParam String start, @RequestParam String end) {

		List<Sale> saleList = saleRepository.findAll();
		List<Event> eventList = new ArrayList<>();

		for (Sale sale : saleList) {
			Delivery delivery = sale.getDelivery();
			Event event = new Event(sale.getCustomerName(), delivery.getDeliveryDate().toString());
			eventList.add(event);

		}
		return eventList;

	}
}
