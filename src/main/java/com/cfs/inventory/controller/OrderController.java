package com.cfs.inventory.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cfs.inventory.domain.model.Delivery;
import com.cfs.inventory.domain.model.Product;
import com.cfs.inventory.domain.model.ProductCategory;
import com.cfs.inventory.domain.model.ProductRepository;
import com.cfs.inventory.domain.model.Sale;
import com.cfs.inventory.domain.model.SaleRepository;
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
		List<Product> productList = productRepository.getProductByCategory(ProductCategory.FINISHED_GOODS);

		return new ModelAndView("createOrder", "productList", productList);

	}
	
	@PostMapping(value = "/orders/delete")
	public String deleteRawMaterial(@RequestParam(name="deleteId") Long saleId) {

		saleRepository.delete(saleId);

		return "redirect:/orders";
	}

	@PostMapping(value = "/addCartItem")
	@ResponseBody
	public void addCartItem(@RequestParam Long id, @RequestParam int quantity) {
		saleApplication.enterItem(id, quantity);
	}

	@GetMapping(value = "/checkout")
	public ModelAndView checkout() {
		return new ModelAndView("checkoutDetails", "cartItems", saleApplication.getCartItems());
	}

	@PostMapping(value = "/saveOrder")
	public String saveOrder(@RequestParam String customerName,@RequestParam String deliveryAddress,@RequestParam Date deliveryDate) {
		Delivery delivery = new Delivery(deliveryAddress, deliveryDate.toLocalDate());
		saleApplication.createNewOrder(customerName, delivery);
		
		return "redirect:/orders";
	}
}
