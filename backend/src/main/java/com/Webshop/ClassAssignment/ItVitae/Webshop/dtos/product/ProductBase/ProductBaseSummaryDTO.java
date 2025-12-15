package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;

import java.util.List;

public record ProductBaseSummaryDTO(
        Long id,
        String productCode,
        String name,
        String description,
        String productBrand,
        List<Product> productVariants,
        List<ProductAttribute> attributes
) {
    public ProductBaseSummaryDTO fromEntity(ProductBase productBase) {
        return new ProductBaseSummaryDTO(
                productBase.getId(),
                productBase.getProductCode(),
                productBase.getName(),
                productBase.getDescription(),
                productBase.getProductBrand(),
                productBase.getProductVariants(),
                productBase.getProductAttributes()
        );
    }
}
