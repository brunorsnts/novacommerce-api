package com.novacommerce.ecommerce_api.repositories;

import com.novacommerce.ecommerce_api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
