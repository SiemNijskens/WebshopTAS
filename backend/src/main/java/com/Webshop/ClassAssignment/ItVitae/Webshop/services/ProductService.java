package com.Webshop.ClassAssignment.ItVitae.Webshop.services;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase.ProductBaseCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase.ProductBaseDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.enums.AttributeType;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.ProductBaseRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductBaseRepository productBaseRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductBaseRepository productBaseRepository) {
        this.productRepository = productRepository;
        this.productBaseRepository = productBaseRepository;
    }

    public ProductBaseDTO createProduct(ProductBaseCreateDTO productBaseCreateDTO) {
        ProductBase productBase = productBaseCreateDTO.toEntity();

        for (ProductAttributeCreateDTO productAttributeCreateDTO : productBaseCreateDTO.attributes()) {
            ProductAttribute productAttribute = productAttributeCreateDTO.toEntity();
            productAttribute.setType(AttributeType.PRODUCT);
            productBase.addProductAttribute(productAttribute);
        }

        for (ProductCreateDTO productCreateDTO : productBaseCreateDTO.products()) {
            Product product = productCreateDTO.toEntity();
            product.setProductBase(productBase);

            for (ProductAttributeCreateDTO productAttributeCreateDTO : productCreateDTO.attributes()) {
                ProductAttribute productAttribute = productAttributeCreateDTO.toEntity();
                productAttribute.setType(AttributeType.VARIANT);
                product.addProductAttribute(productAttribute);
            }
            productBase.addProductVariant(product);
        }

        ProductBase savedProductBase = productBaseRepository.save(productBase);

        return ProductBaseDTO.fromEntity(savedProductBase);
    }

}
