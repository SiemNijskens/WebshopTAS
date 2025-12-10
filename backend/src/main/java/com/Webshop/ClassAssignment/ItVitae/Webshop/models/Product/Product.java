package com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.HashMap;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String brand;

    private String productCode;

    private float price;

    private int stock;

    private boolean sale;

    private float salePercentage;

    private HashMap<String, String> properties = new HashMap<>();

}