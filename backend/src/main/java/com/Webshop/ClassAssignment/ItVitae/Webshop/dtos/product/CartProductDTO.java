package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeSummaryDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;

import java.util.List;

public record CartProductDTO(
        Long productBaseId,
        Long productId,
        String name,
        float price,
        float salePercentage,
        List<ProductAttributeSummaryDTO> attributes
) {
    public static CartProductDTO fromEntity(Product variant) {
        return new CartProductDTO(
                variant.getProductBase().getId(),
                variant.getId(),
                variant.getProductBase().getName(),
                variant.getPrice(),
                variant.getSalePercentage(),
                variant.getProductAttributes().stream()
                        .map(ProductAttributeSummaryDTO::fromEntity)
                        .toList()
        );
    }
}
