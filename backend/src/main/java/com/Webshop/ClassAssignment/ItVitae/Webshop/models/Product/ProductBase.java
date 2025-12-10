package com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class ProductBase {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    private String description;

    private String productBrand;

    @ManyToMany
    private List<ProductAttribute> productAttributes;

}
