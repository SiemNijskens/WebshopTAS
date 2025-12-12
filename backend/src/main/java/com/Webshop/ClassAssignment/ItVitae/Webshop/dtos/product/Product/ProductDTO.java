package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;
import java.util.List;

public record ProductDTO(
        Long id,
        float price,
        boolean sale,
        float salePercentage,
        int stock,
        ProductBase product,
        List<ProductAttribute> productVariantAttributes
    )
    {


}
