package com.cfs.inventory.service;

import static com.cfs.inventory.model.OrderType.PER_PIECE;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.cfs.inventory.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleApplicationService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SaleApplicationService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    private void enterItem(Sale sale, List<CartItem> cartItems) {
        cartItems.forEach(cartItem -> {
            sale.addToOrder(cartItem.getProduct(), cartItem.getQuantity(), PER_PIECE);
            Product product = productRepository.getOne(cartItem.getProduct().getId());
            product.deductStock(cartItem.getQuantity());
        });

    }

    @Transactional
    public SaleConfirmation createNewOrder(String customerName, Delivery delivery, List<CartItem> cartItems) {
        if (cartItems.size() <= 0) {
            throw new IllegalStateException("Cannot create new order with empty cart");
        }

        Sale sale = new Sale(customerName, delivery);
        enterItem(sale, cartItems);
        saleRepository.save(sale);
        SaleConfirmation confirmation = new SaleConfirmation(sale.getItems(), sale.getCustomerName());

        return confirmation;

    }

    public void changeStatus(Long orderId, Status status) {

        Sale sale = saleRepository.getOne(orderId);

        if (status.equals(Status.CANCELLED)) {
            returnAllItems(sale);
        }
        sale.setStatus(status);
        saleRepository.save(sale);

    }

    private void returnAllItems(Sale sale) {
        List<SalesLineItem> items = sale.getItems();
        for (SalesLineItem item : items) {
            sale.returnItem(item.getProduct(), item.getQuantity());
        }
    }
}
