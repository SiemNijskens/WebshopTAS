package com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;

import jakarta.persistence.*;
import java.util.List;

@Table(name="Product")
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long productId;

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

    public Product(Long id, float price, boolean sale, float salePercentage, int stock, ProductBase product, List<ProductAttribute> productVariantAttributes) {
        this.productId = id;
        this.price = price;
        this.sale = sale;
        this.salePercentage = salePercentage;
        this.stock = stock;
        this.product = product;
        this.productVariantAttributes = productVariantAttributes;
    }

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public float getSalePercentage() {
        return salePercentage;
    }

    public void setSalePercentage(float salePercentage) {
        this.salePercentage = salePercentage;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ProductBase getProduct() {
        return product;
    }

    public void setProduct(ProductBase product) {
        this.product = product;
    }

    public List<ProductAttribute> getProductVariantAttributes() {
        return productVariantAttributes;
    }

    public void setProductVariantAttributes(List<ProductAttribute> productVariantAttributes) {
        this.productVariantAttributes = productVariantAttributes;
    }

    public void addProductVariantAttribute(ProductAttribute productAttribute) {
        this.productVariantAttributes.add(productAttribute);
    }
}
