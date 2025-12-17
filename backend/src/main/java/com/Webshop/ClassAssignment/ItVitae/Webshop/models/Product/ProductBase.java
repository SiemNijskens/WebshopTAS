package com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;

import com.Webshop.ClassAssignment.ItVitae.Webshop.enums.productType;
import jakarta.persistence.*;
import java.util.List;

@Table(name="BaseProduct")
@Entity
public class ProductBase {

    @GeneratedValue
    @Id
    private Long id;

    private String productCode;

    private String defaultImageURL;

    @Enumerated (EnumType.STRING)
    private productType type;

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

    public ProductBase(Long id, String productCode, String defaultImageURL, productType type, String name, String description, String productBrand, List<Product> productVariants, List<ProductAttribute> productAttributes) {
        this.id = id;
        this.productCode = productCode;
        this.defaultImageURL = defaultImageURL;
        this.type = type;
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

    public String getDefaultImageURL() {
        return defaultImageURL;
    }

    public void setDefaultImageURL(String defaultImageURL) {
        this.defaultImageURL = defaultImageURL;
    }

    public productType getType() {
        return type;
    }

    public void setType(productType type) {
        this.type = type;
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

    public void addProductVariants(Product productVariant){
        this.productVariants.add(productVariant);
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public void addProductAttribute(ProductAttribute productAttribute){
        this.productAttributes.add(productAttribute);
    }

}
