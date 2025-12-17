package com.Webshop.ClassAssignment.ItVitae.Webshop.repositories;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
