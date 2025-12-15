package com.Webshop.ClassAssignment.ItVitae.Webshop.repositories;

import com.Webshop.ClassAssignment.ItVitae.Webshop.models.Product.ProductBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBaseRepository extends JpaRepository<ProductBase, Long> {
}
