package com.Webshop.ClassAssignment.ItVitae.Webshop.controllers;

import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.ProductDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.Product.StockDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase.ProductBaseCreateDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase.ProductBaseCreateDTOtwo;
import com.Webshop.ClassAssignment.ItVitae.Webshop.dtos.product.ProductBase.ProductBaseDTO;
import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;
import com.Webshop.ClassAssignment.ItVitae.Webshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductBaseDTO> createProduct(@RequestBody ProductBaseCreateDTO productBaseCreateDTO) {
        ProductBaseDTO productBase = productService.createProduct(productBaseCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productBase);
    }

    @PostMapping("/{create}")
    public ResponseEntity<ProductBaseDTO> createProducts(@RequestBody ProductBaseCreateDTOtwo productBaseCreateDTO) {
        ProductBaseDTO productBase = productService.createProducts(productBaseCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productBase);
    }

    @GetMapping
    public ResponseEntity<List<ProductBaseDTO>> getAllProducts() {
        List<ProductBaseDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductBaseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PatchMapping("/stock")
    public ResponseEntity<ProductDTO> updateStock(@RequestBody StockDTO stockDTO) {
        ProductDTO stockUpdate = productService.updateStock(stockDTO);
        return ResponseEntity.ok(stockUpdate);
    }
}
