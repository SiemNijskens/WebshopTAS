package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.enums.AttributeType;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;

import java.util.List;

public record ProductDTO(
        Long id,
        String imageURL,
        float price,
        float salePercentage,
        int stock,
        List<ProductAttributeDTO> attributes
) {
    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getImageURL(),
                product.getPrice(),
                product.getSalePercentage(),
                product.getStock(),
                product.getProductAttributes().stream()
                        .filter(atr -> atr.getType() == AttributeType.VARIANT)
                        .map(ProductAttributeDTO::fromEntity)
                        .toList()
        );
    }
}