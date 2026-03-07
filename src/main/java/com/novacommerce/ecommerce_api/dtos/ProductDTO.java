package com.novacommerce.ecommerce_api.dtos;

import com.novacommerce.ecommerce_api.entities.Product;

import java.math.BigDecimal;

public record ProductDTO(Long id, String name, String description, BigDecimal price, CategoryDTO categoryDTO) {
    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), new CategoryDTO(product.getCategory()));
    }
}
