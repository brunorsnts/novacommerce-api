package com.novacommerce.ecommerce_api.dtos;

import jakarta.validation.constraints.Positive;

public record OrderItemInsertDTO(
        Long productId,
        @Positive(message = "A quantidade deve ser maior que zero") Integer quantity
) {
}
