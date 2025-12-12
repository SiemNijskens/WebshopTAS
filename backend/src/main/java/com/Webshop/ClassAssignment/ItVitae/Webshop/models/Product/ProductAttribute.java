package com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ProductAttribute {

    @Id
    @GeneratedValue
    private Long id;

    private String attribute;

    private String value;
}