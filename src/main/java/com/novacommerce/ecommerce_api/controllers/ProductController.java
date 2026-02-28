package com.novacommerce.ecommerce_api.controllers;

import com.novacommerce.ecommerce_api.entities.Product;
import com.novacommerce.ecommerce_api.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = repository.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return ResponseEntity.ok(product);
    }
}
