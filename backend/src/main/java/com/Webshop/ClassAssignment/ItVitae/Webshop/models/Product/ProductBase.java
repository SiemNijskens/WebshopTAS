package com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;

import jakarta.persistence.*;
import java.util.List;

@Table(name="BaseProduct")
@Entity
public class ProductBase {

    @GeneratedValue
    @Id
    private Long id;

    private String productCode;

    private String name;

    private String description;

    private String productBrand;

    // misschien kan deze tabel mapped by worden door "private ProductBase product" in Product.java
    @OneToMany
    private List<Product> productVariants;

    @ManyToMany
    @JoinTable(
            name = "base_product_attribute",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private List<ProductAttribute> productAttributes;
}
