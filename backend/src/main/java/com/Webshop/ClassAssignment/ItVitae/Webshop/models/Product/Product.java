package com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productCode;

    private float price;

    private boolean sale;

    private float salePercentage;

    private int stock;

    private ProductBase product;

    @ManyToMany
    private List<ProductAttribute> variant;
}

//- id: Long
//- productCode: String
//- price: Float
//- sale: Boolean
//- salePercentage: int
//- stock int
//- product ProductBase
//- variant: List<ProductAttribute>