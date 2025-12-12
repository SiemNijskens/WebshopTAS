package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;

import java.util.List;

public record ProductBaseCreateDTO(
        String productCode,
        String name,
        String description,
        String productBrand,
        List<Product> productVariants,
        List<ProductAttribute> productAttributes) {
    public ProductBase toEntity() {
        ProductBase productBase = new ProductBase();
        productBase.setProductCode(this.productCode);
        productBase.setName(this.name);
        productBase.setDescription(this.description);
        productBase.setProductBrand(this.productBrand);
        productBase.setProductVariants(this.productVariants);
        productBase.setProductAttributes(this.productAttributes);
        return productBase;
    }
}