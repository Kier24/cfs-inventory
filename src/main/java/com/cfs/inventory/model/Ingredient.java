package com.cfs.inventory.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private RawMaterial rawMaterial;
    private int quantity;

    public Ingredient(RawMaterial rawMaterial, int quantity) {
        this.rawMaterial = rawMaterial;
        this.quantity = quantity;
    }

    protected Ingredient() {

    }
}
