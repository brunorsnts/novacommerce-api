package com.novacommerce.ecommerce_api.controllers;

import com.novacommerce.ecommerce_api.dtos.CategoryDTO;
import com.novacommerce.ecommerce_api.dtos.ProductDTO;
import com.novacommerce.ecommerce_api.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PagedModel<ProductDTO>> findAll(
            @PageableDefault(size = 10, sort = "name")
            Pageable pageable) {
        Page<ProductDTO> products = service.findAll(pageable);
        PagedModel<ProductDTO> page = new PagedModel<>(products);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO product = service.findById(id);
        return ResponseEntity.ok(product);
    }
}
