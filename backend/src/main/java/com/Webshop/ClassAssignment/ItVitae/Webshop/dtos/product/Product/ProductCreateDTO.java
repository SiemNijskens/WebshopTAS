package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;
import java.util.List;

public record ProductCreateDTO(
        float price,
        boolean sale,
        float salePercentage,
        int stock,
        ProductBase product,
        List<ProductAttribute> productVariantAttributes

) {
    public Product toEntity(){
        Product product = new Product();
        product.setPrice(this.price);
        product.setSale(this.sale);
        product.setSalePercentage(this.salePercentage);
        product.setStock(this.stock);
        return product;
    }
}
