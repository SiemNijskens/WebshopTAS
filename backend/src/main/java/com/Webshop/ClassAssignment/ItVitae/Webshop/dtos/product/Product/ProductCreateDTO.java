package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;
import java.util.List;

public record ProductCreateDTO(
        String imageURL,
        float price,
        float salePercentage,
        int stock,
        List<ProductAttributeCreateDTO> attributes

) {
    public Product toEntity(){
        Product product = new Product();
        product.setImageURL(this.imageURL);
        product.setPrice(this.price);
        product.setSalePercentage(this.salePercentage);
        product.setStock(this.stock);
        return product;
    }
}
