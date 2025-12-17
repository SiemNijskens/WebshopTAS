package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;

import java.util.List;

public record ProductSummaryDTO(
        Long id,
        float price,
        float salePercentage,
        int stock,
        String color,
        ProductBase product
//        List<ProductAttribute> productVariantAttributes
) {
    public ProductSummaryDTO fromEntity(Product product) {
        return new ProductSummaryDTO(
                product.getId(),
                product.getPrice(),
                product.getSalePercentage(),
                product.getStock(),
                product.getColor(),
                product.getProduct()
//                product.getProductVariantAttributes()
        );
    }
}
