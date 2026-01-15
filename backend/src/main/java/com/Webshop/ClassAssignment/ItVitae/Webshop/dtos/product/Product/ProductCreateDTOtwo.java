package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;

import java.util.List;

public record ProductCreateDTOtwo(
        int key,
        String imageURL,
        String price,
        String stock,
        ProductAttributeCreateDTO variantAttributeOne,
        ProductAttributeCreateDTO variantAttributeTwo
//        List<ProductAttributeCreateDTO> attributes

) {
    public Product toEntity(){
        Product product = new Product();
        product.setImageURL(this.imageURL);
        product.setPrice(Float.parseFloat(this.price));
        product.setStock(Integer.parseInt(this.stock));
        return product;
    }
}
