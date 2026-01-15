package com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductCreateDTOtwo;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;

import java.util.List;

public record ProductBaseCreateDTOtwo(
//        String productCode,
        String defaultImageURL,
        String category,
        String name,
        String description,
        String productBrand,
        List<ProductAttributeCreateDTO> productAttributes,
        List<ProductCreateDTOtwo> products
) {
    public ProductBase toEntity() {
        ProductBase productBase = new ProductBase();
//        productBase.setProductCode(this.productCode);
        productBase.setDefaultImageURL(this.defaultImageURL);
        productBase.setCategory(this.category);
        productBase.setName(this.name);
        productBase.setDescription(this.description);
        productBase.setProductBrand(this.productBrand);
        return productBase;
    }
}

//{
// "defaultImageURL":"url",
// "name":"Shirt",
// "productBrand":"Nike",
// "description":"ShirtDescription",
// "category":"top",
//  "productAttributes":[
//      {"productAttribute0":{
//          "attribute":"material",
//          "value":"cotton",
//          "type":"PRODUCT"}},
//      {"productAttribute1":{
//          "attribute":"neck type",
//          "value":"crew",
//          "type":"PRODUCT"}}
//  ],
//  "products":[{
//      "key":0,"variantAttributeOne":{"attribute":"size","value":"small","type":"VARIANT"},"variantAttributeTwo":{"attribute":"color","value":"blue","type":"VARIANT"},"stock":"10","price":"10","imageURL":"url"},
//      {"key":1,"variantAttributeOne":{"attribute":"size","value":"small","type":"VARIANT"},"variantAttributeTwo":{"attribute":"color","value":"red","type":"VARIANT"},"stock":"10","price":"10","imageURL":"url"},
//      {"key":2,"variantAttributeOne":{"attribute":"size","value":"medium","type":"VARIANT"},"variantAttributeTwo":{"attribute":"color","value":"blue","type":"VARIANT"},"stock":"10","price":"10","imageURL":"url"},
//      {"key":3,"variantAttributeOne":{"attribute":"size","value":"medium","type":"VARIANT"},"variantAttributeTwo":{"attribute":"color","value":"red","type":"VARIANT"},"stock":"10","price":"10","imageURL":"url"}
//      ]}
