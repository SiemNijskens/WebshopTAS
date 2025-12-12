package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;

public record ProductAttributeDTO(
        Long id,
        String attribute,
        String value
) {
    public ProductAttributeDTO fromEntity(ProductAttribute productAttribute){
        return new ProductAttributeDTO(
                productAttribute.getId(),
                productAttribute.getAttribute(),
                productAttribute.getValue()
        );
    }
}
