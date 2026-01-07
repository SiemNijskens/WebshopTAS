package com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product;

import com.Webshop.ClassAssignment.ItVitae.Webshop.enums.ProductType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name="BaseProduct")
@Entity
public class ProductBase {

    @GeneratedValue
    @Id
    private Long id;

    private String productCode;

    private String defaultImageURL;

//    @Enumerated (EnumType.STRING)
//    private ProductType type;

    private String category;

    private String name;

    private String description;

    private String productBrand;

    // misschien kan deze tabel mapped by worden door "private ProductBase product" in Product.java
    @OneToMany(mappedBy = "productBase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> productVariants = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(
//            name = "base_product_attribute",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "attribute_id")
//    )
    @OneToMany(mappedBy = "productBase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAttribute> productAttributes = new ArrayList<>();

//    public ProductBase(Long id, String productCode, String name, String description, String productBrand, List<Product> productVariants, List<ProductAttribute> productAttributes) {
//        this.id = id;
//        this.productCode = productCode;
//        this.name = name;
//        this.description = description;
//        this.productBrand = productBrand;
//        this.productVariants = productVariants;
//        this.productAttributes = productAttributes;
//    }
//
//    public ProductBase() {
//    }

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

//    public ProductType getType() {
//        return type;
//    }
//    public void setType(ProductType type) {
//        this.type = type;
//    }


    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
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

    public void addProductAttribute(ProductAttribute attribute) {
        attribute.setProductBase(this);
        productAttributes.add(attribute);
    }

    public void addProductVariant(Product variant) {
        variant.setProductBase(this);
        productVariants.add(variant);
    }

}
