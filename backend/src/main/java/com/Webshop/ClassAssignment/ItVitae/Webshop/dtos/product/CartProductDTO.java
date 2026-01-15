package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeSummaryDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;

import java.util.List;

public record CartProductDTO(
        Long productBaseId,
        Long productId,
        String name,
        String imageURL,
        String description,
        float price,
        float salePercentage,
        int stock,
        List<ProductAttributeSummaryDTO> attributes
) {
    public static CartProductDTO fromEntity(Product variant) {
        return new CartProductDTO(
                variant.getProductBase().getId(),
                variant.getId(),
                variant.getProductBase().getName(),
                variant.getImageURL(),
                variant.getProductBase().getDescription(),
                variant.getPrice(),
                variant.getSalePercentage(),
                variant.getStock(),
                variant.getProductAttributes().stream()
                        .map(ProductAttributeSummaryDTO::fromEntity)
                        .toList()
        );
    }
}
