package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.enums.AttributeType;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;

import java.util.List;

public record ProductBaseDTO(
        Long id,
        String productCode,
        String defaultImageURL,
        String name,
        String description,
        String productBrand,
        List<ProductDTO> productVariants,
        List<ProductAttributeDTO> attributes
) {
    public static ProductBaseDTO fromEntity(ProductBase productBase) {
        return new ProductBaseDTO(
                productBase.getId(),
                productBase.getProductCode(),
                productBase.getDefaultImageURL(),
                productBase.getName(),
                productBase.getDescription(),
                productBase.getProductBrand(),
                productBase.getProductVariants().stream()
                                .map(ProductDTO::fromEntity)
                                        .toList(),
                productBase.getProductAttributes().stream()
                        .filter(atr -> atr.getType() == AttributeType.PRODUCT)
                        .map(ProductAttributeDTO::fromEntity)
                        .toList()
        );
    }
}
