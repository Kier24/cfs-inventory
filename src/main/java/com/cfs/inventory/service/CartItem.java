package com.cfs.inventory.service;

import java.math.BigDecimal;

import com.cfs.inventory.model.ProducedGood;

import com.cfs.inventory.model.Product;
import lombok.Getter;

@Getter
public class CartItem {

    private Product product;
    private String name;
    private String containerType;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.name = product.getName();
        this.containerType = product.getContainer().getName();
        this.quantity = quantity;
    }

}
