package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;

public record ProductAttributeCreateDTO(
        Long id,
        String attribute,
        String value
) {
    public ProductAttribute toEntity(){
        ProductAttribute productAttribute = new ProductAttribute();
        productAttribute.setAttribute(this.attribute);
        productAttribute.setValue(this.value);
        return productAttribute;
    }
}
