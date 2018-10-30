package com.cfs.inventory.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.cfs.inventory.model.Delivery;
import com.cfs.inventory.model.Product;
import com.cfs.inventory.model.ProductCategory;
import com.cfs.inventory.model.ProductRepository;
import com.cfs.inventory.model.Sale;
import com.cfs.inventory.model.SaleRepository;
import com.cfs.inventory.dto.SaleDto;

import com.cfs.inventory.service.SaleApplicationService;

@Controller
public class OrderController {

	@Autowired
	private SaleRepository saleRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SaleApplicationService saleApplication;

	@GetMapping(value = "/orders")
	public ModelAndView getOrders() {

		List<Sale> saleList = saleRepository.findAll();

		return new ModelAndView("order", "orderList", saleList);

	}

	@GetMapping(value = "/createOrder")
	public ModelAndView createOrder() {

		return new ModelAndView("createOrder", "productList", null);

	}

	@GetMapping(value = "viewCart")
	public ModelAndView viewCartItems() {
		return new ModelAndView("orderCart", "cartItems", saleApplication.getCartItems());
	}

	@PostMapping(value = "/orders/delete")
	public String deleteRawMaterial(@RequestParam(name = "deleteId") Long saleId) {

		saleRepository.deleteById(saleId);

		return "redirect:/orders";
	}

	@PostMapping(value = "/addCartItem")
	@ResponseBody
	public void addCartItem(@RequestParam Long id, @RequestParam int quantity) {
		saleApplication.enterItem(id, quantity);
	}

	@GetMapping(value = "/orders/{id}")
	@ResponseBody
	public SaleDto getOrder(@PathVariable Long id) {

		Sale sale = saleRepository.getOne(id);
		return new SaleDto(sale.getCustomerName(), sale.getDelivery(), sale.getItems());
	}

	@PostMapping(value = "/saveOrder")
	public String saveOrder(@RequestParam String customerName, @RequestParam String deliveredBy,
			@RequestParam String deliveryAddress, @RequestParam Date deliveryDate) {
		Delivery delivery = new Delivery(deliveryAddress, deliveryDate.toLocalDate(), deliveredBy);
		saleApplication.createNewOrder(customerName, delivery);

		return "redirect:/orders";
	}
}
