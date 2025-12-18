package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;

import java.util.List;

public record ProductSummaryDTO(
        Long id,
        String imageURL,
        float price,
        float salePercentage,
        int stock,
        ProductBase product
//        List<ProductAttribute> productVariantAttributes
) {
    public ProductSummaryDTO fromEntity(Product product) {
        return new ProductSummaryDTO(
                product.getId(),
                product.getImageURL(),
                product.getPrice(),
                product.getSalePercentage(),
                product.getStock(),
                product.getProduct()
//                product.getProductVariantAttributes()
        );
    }
}
