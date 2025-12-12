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

    public ProductBase(Long id, String productCode, String name, String description, String productBrand, List<Product> productVariants, List<ProductAttribute> productAttributes) {
        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.productBrand = productBrand;
        this.productVariants = productVariants;
        this.productAttributes = productAttributes;
    }

    public ProductBase() {
    }

    public Long getId() {
        return id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public List<Product> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(List<Product> productVariants) {
        this.productVariants = productVariants;
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }
}
