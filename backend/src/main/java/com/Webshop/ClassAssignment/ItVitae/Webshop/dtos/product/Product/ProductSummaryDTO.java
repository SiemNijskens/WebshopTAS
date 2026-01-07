package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeSummaryDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase.ProductBaseDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;

import java.util.List;

public record ProductSummaryDTO(
//        Long id,
        String name,
        String description,
        String brand,
        String imageURL,
        float price,
        List<ProductAttributeSummaryDTO> attributes

//        List<ProductAttribute> productVariantAttributes
) {
    public static ProductSummaryDTO fromEntity(Product product) {
        return new ProductSummaryDTO(
//                product.getId(),
                product.getProductBase().getName(),
                product.getProductBase().getDescription(),
                product.getProductBase().getProductBrand(),
                product.getImageURL(),
                product.getPrice(),
                product.getProductAttributes().stream().map(ProductAttributeSummaryDTO::fromEntity).toList()
        );
    }
}
