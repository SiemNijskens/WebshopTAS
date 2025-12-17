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

    public ProductAttribute(Long id, String attribute, String value) {
        this.id = id;
        this.attribute = attribute;
        this.value = value;
    }

    public ProductAttribute() {
    }

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
}