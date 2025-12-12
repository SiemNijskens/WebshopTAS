package com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;

import jakarta.persistence.*;
import java.util.List;

@Table(name="Product")
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private float price;

    private boolean sale;

    private float salePercentage;

    private int stock;

    @ManyToOne
    private ProductBase product;

    @ManyToMany
    @JoinTable(
            name = "product_variant_attributes",
            joinColumns = @JoinColumn(name = "product_attribute_id"),
            inverseJoinColumns = @JoinColumn(name = "variant_id")
    )
    private List<ProductAttribute> productVariantAttributes;
}
