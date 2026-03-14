package com.novacommerce.ecommerce_api.dtos;

import com.novacommerce.ecommerce_api.entities.Category;
import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(Long id,
                          @NotBlank(message = "Campo requerido") String name) {
    public CategoryDTO(Category category) {
        this(category.getId(), category.getName());
    }
}
