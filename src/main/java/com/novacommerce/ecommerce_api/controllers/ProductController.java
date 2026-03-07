package com.novacommerce.ecommerce_api.controllers;

import com.novacommerce.ecommerce_api.entities.Product;
import com.novacommerce.ecommerce_api.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PagedModel<Product>> findAll(
            @PageableDefault(size = 10, sort = "name")
            Pageable pageable) {
        Page<Product> products = service.findAll(pageable);
        PagedModel<Product> page = new PagedModel<>(products);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = service.findById(id);
        return ResponseEntity.ok(product);
    }
}
