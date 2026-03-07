package com.novacommerce.ecommerce_api.dtos;

import com.novacommerce.ecommerce_api.entities.Category;

public record CategoryDTO(Long id, String name) {
    public CategoryDTO(Category category) {
        this(category.getId(), category.getName());
    }
}
