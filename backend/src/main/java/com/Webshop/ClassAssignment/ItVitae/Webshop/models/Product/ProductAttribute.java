package com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;

import com.Webshop.ClassAssignment.ItVitae.Webshop.enums.AttributeType;
import jakarta.persistence.*;

@Entity
public class ProductAttribute {

    @Id
    @GeneratedValue
    private Long id;

    private String attribute;

    private String value;

    @Enumerated(EnumType.STRING)
    private AttributeType type;

//    public ProductAttribute(Long id, String attribute, String value) {
//        this.id = id;
//        this.attribute = attribute;
//        this.value = value;
//    }
//
//    public ProductAttribute() {
//    }

    @ManyToOne
    private ProductBase productBase;

    @ManyToOne
    private Product product;

    public Long getId() {
        return id;
    }

    public String getAttribute() {
        return attribute;
    }
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public AttributeType getType() {
        return type;
    }
    public void setType(AttributeType type) {
        this.type = type;
    }

    public ProductBase getProductBase() {
        return productBase;
    }
    public void setProductBase(ProductBase productBase) {
        this.productBase = productBase;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}