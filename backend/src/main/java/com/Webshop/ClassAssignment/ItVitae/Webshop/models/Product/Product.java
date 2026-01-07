package com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name="Product")
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long productId;

    private String imageURL;

    private float price;

    private float salePercentage;

    private int stock;

    @ManyToOne
    private ProductBase productBase;

//    @ManyToMany
//    @JoinTable(
//            name = "product_variant_attributes",
//            joinColumns = @JoinColumn(name = "product_attribute_id"),
//            inverseJoinColumns = @JoinColumn(name = "variant_id")
//    )
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAttribute> productAttributes = new ArrayList<>();

    public Product(Long productId, String imageURL, float price, float salePercentage, int stock, ProductBase productBase, List<ProductAttribute> productAttributes) {
        this.productId = productId;
        this.imageURL = imageURL;
        this.price = price;
        this.salePercentage = salePercentage;
        this.stock = stock;
        this.productBase = productBase;
        this.productAttributes = productAttributes;
    }

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
        return productBase;
    }

    public void setProduct(ProductBase productBase) {
        this.productBase = productBase;
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

//    public void addProductAttribute(ProductAttribute productAttribute) {
//        this.productAttributes.add(productAttribute);
//    }

    public ProductBase getProductBase() {
        return productBase;
    }
    public void setProductBase(ProductBase productBase) {
        this.productBase = productBase;
    }

    public void addProductAttribute(ProductAttribute productAttribute) {
        productAttribute.setProduct(this);
        productAttributes.add(productAttribute);
    }
}
