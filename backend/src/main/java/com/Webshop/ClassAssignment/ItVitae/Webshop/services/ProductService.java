package com.Webshop.ClassAssignment.ItVitae.Webshop.services;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductCreateDTOtwo;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductDTO;
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

        // ProductCreateDTOtwo bevat wel de key
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
        System.out.println(productBaseCreateDTO.toString());

        for (ProductAttributeCreateDTO productAttributeCreateDTO : productBaseCreateDTO.productAttributes()) {
            ProductAttribute productAttribute = productAttributeCreateDTO.toEntity();
            productAttribute.setType(AttributeType.PRODUCT);
//            productAttribute.setValue(productAttributeCreateDTO.value());
//            productAttribute.setAttribute(productAttributeCreateDTO.attribute());
            productBase.addProductAttribute(productAttribute);
            System.out.println("hoi ik ben een sout voor product attributes");
            System.out.println(productAttributeCreateDTO.toString());
        }

        for (ProductCreateDTOtwo productCreateDTO : productBaseCreateDTO.products()) {
            Product product = productCreateDTO.toEntity();
            product.setProductBase(productBase);
            ProductAttribute variantAttributeOne = productCreateDTO.variantAttributeOne().toEntity();
            ProductAttribute variantAttributeTwo = productCreateDTO.variantAttributeTwo().toEntity();
            product.addProductAttribute(variantAttributeOne);
            product.addProductAttribute(variantAttributeTwo);
            System.out.println("Hoi ik ben een sout");
            System.out.println(variantAttributeOne.getValue());
            System.out.println(variantAttributeTwo.getValue());
//            product.addProductAttribute(productCreateDTO.variantAttributeTwo().toEntity());
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

    public ProductDTO updateStock(Long variantId, int stock) {
        Product product = productRepository.findById(variantId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + variantId));

        product.setStock(stock);
        Product savedProduct = productRepository.save(product);

        return  ProductDTO.fromEntity(savedProduct);
    }
}
