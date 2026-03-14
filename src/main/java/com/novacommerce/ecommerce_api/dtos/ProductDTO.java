package com.novacommerce.ecommerce_api.dtos;

import com.novacommerce.ecommerce_api.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductDTO(Long id,
                         @NotBlank(message = "Campo requerido") String name,
                         @NotBlank(message = "Campo requerido") String description,
                         @Positive(message = "O valor deve ser maior do que 0") BigDecimal price,
                         CategoryDTO category) {
    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), new CategoryDTO(product.getCategory()));
    }
}
