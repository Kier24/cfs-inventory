package com.cfs.inventory.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfs.inventory.domain.model.Delivery;
import com.cfs.inventory.domain.model.Product;
import com.cfs.inventory.domain.model.ProductRepository;
import com.cfs.inventory.domain.model.Sale;
import com.cfs.inventory.domain.model.SaleRepository;

@Service
public class SaleApplicationService {

	private final SaleRepository saleRepository;
	private final ProductRepository productRepository;
	private final List<CartItem> cartItemsList = new ArrayList<>();

	@Autowired
	public SaleApplicationService(SaleRepository saleRepository, ProductRepository productRepository) {
		this.saleRepository = saleRepository;
		this.productRepository = productRepository;
	}

	// TODO: Plan if it should be List or Map. Possible conflict if user want to
	// remove a certain product or edit quantity;
	public void enterItem(Long id, int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity must be greather than zero but was " + quantity);
		}
		Product product = productRepository.getOne(id);
		if (product.getQuantity() < quantity) {
			throw new IllegalStateException("Input quantity cannot be greater than stock quantity.");
		}
		cartItemsList.add(new CartItem(product, quantity));

	}

	public List<CartItem> getCartItems() {
		return new ArrayList<>(cartItemsList);
	}

	@Transactional
	public SaleConfirmation createNewOrder(String customerName, Delivery delivery) {

		if (cartItemsList.size() <= 0) {
			throw new IllegalStateException("Cannot create new order with empty cart");
		}

		Sale sale = new Sale(customerName, delivery);

		for (CartItem cartItem : cartItemsList) {
			Product product = productRepository.getOne(cartItem.getProduct().getId());
			sale.setItemQuantity(product, cartItem.getQuantity());
			product.deductStock(cartItem.getQuantity());
		}

		saleRepository.save(sale);
		SaleConfirmation confirmation = new SaleConfirmation(sale.getItems(), sale.getCustomerName());
		cartItemsList.clear();

		return confirmation;

	}
}
