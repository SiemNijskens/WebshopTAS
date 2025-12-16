package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;

import java.util.List;

public record ProductBaseCreateDTO(
        String productCode,
        String name,
        String description,
        String productBrand,
        List<ProductCreateDTO> products,
        List<ProductAttributeCreateDTO> productAttributes
) {
    public ProductBase toEntity() {
        ProductBase productBase = new ProductBase();
        productBase.setProductCode(this.productCode);
        productBase.setName(this.name);
        productBase.setDescription(this.description);
        productBase.setProductBrand(this.productBrand);
        return productBase;
    }
}