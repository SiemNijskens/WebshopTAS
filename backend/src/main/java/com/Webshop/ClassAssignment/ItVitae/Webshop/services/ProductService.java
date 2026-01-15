package com.Webshop.ClassAssignment.ItVitae.Webshop.services;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductCreateDTOtwo;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.StockDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductAttribute.ProductAttributeCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase.ProductBaseCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase.ProductBaseCreateDTOtwo;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase.ProductBaseDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.enums.AttributeType;
import com.Webshop.ClassAssignment.ItVitae.Webshop.exceptions.ProductNotFoundException;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductAttribute;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.ProductBaseRepository;
import com.Webshop.ClassAssignment.ItVitae.Webshop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        for (ProductAttributeCreateDTO productAttributeCreateDTO : productBaseCreateDTO.productAttributes()) {
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

    public ProductBaseDTO createProducts(ProductBaseCreateDTOtwo productBaseCreateDTO) {
        ProductBase productBase = productBaseCreateDTO.toEntity();

        for (ProductAttributeCreateDTO productAttributeCreateDTO : productBaseCreateDTO.productAttributes()) {
            ProductAttribute productAttribute = productAttributeCreateDTO.toEntity();
            productAttribute.setType(AttributeType.PRODUCT);
            productAttribute.setProductBase(productBase);
            productBase.addProductAttribute(productAttribute);
        }

        for (ProductCreateDTOtwo productCreateDTO : productBaseCreateDTO.products()) {
            Product product = productCreateDTO.toEntity();
            product.setProductBase(productBase);
            ProductAttribute variantAttributeOne = productCreateDTO.variantAttributeOne().toEntity();
            ProductAttribute variantAttributeTwo = productCreateDTO.variantAttributeTwo().toEntity();
            product.addProductAttribute(variantAttributeOne);
            product.addProductAttribute(variantAttributeTwo);
            product.setSalePercentage(1);
            productBase.addProductVariant(product);
        }

        ProductBase savedProductBase = productBaseRepository.save(productBase);

        return ProductBaseDTO.fromEntity(savedProductBase);
    }

    public List<ProductBaseDTO> findAll() {
        return productBaseRepository.findAll().stream().map(ProductBaseDTO::fromEntity).toList();
    }

    public ProductBaseDTO findById(Long id) {
        ProductBase productBase = productBaseRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
        return ProductBaseDTO.fromEntity(productBase);
    }

    public ProductDTO updateStock(StockDTO stockDTO) {
        Product product = productRepository.findById(stockDTO.variantId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + stockDTO.variantId()));

        product.setStock(stockDTO.newStock());
        Product savedProduct = productRepository.save(product);

        return  ProductDTO.fromEntity(savedProduct);
    }
}
