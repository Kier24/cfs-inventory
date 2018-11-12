package com.cfs.inventory.service;

import static com.cfs.inventory.model.OrderType.PER_PIECE;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfs.inventory.model.Delivery;
import com.cfs.inventory.model.ProducedGood;
import com.cfs.inventory.model.ProducedGoodRepository;
import com.cfs.inventory.model.Sale;
import com.cfs.inventory.model.SaleRepository;

@Service
public class SaleApplicationService {

	private final SaleRepository saleRepository;
	private final ProducedGoodRepository producedGoodRepository;
	private final List<CartItem> cartItemsList = new ArrayList<>();

	@Autowired
	public SaleApplicationService(SaleRepository saleRepository, ProducedGoodRepository producedGoodRepository) {
		this.saleRepository = saleRepository;
		this.producedGoodRepository = producedGoodRepository;
	}

	// TODO: Plan if it should be List or Map. Possible conflict if user want to
	// remove a certain product or edit quantity;
	public void enterItem(Long id, int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity must be greather than zero but was " + quantity);
		}
		ProducedGood item = producedGoodRepository.getOne(id);
		if (item.getQuantity() < quantity) {
			throw new IllegalStateException("Input quantity cannot be greater than stock quantity.");
		}
		cartItemsList.add(new CartItem(item, quantity));

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
			ProducedGood product = producedGoodRepository.getOne(cartItem.getProducedGood().getId());
			sale.addToOrder(product, cartItem.getQuantity(),PER_PIECE);
			sale.confirmOrder();
			product.deductStock(cartItem.getQuantity());
		}

		saleRepository.save(sale);
		SaleConfirmation confirmation = new SaleConfirmation(sale.getItems(), sale.getCustomerName());
		cartItemsList.clear();

		return confirmation;

	}
}
