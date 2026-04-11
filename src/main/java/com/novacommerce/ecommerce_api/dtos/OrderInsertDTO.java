package com.novacommerce.ecommerce_api.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderInsertDTO(
        Long clientId,
        @NotEmpty(message = "O pedido deve ter pelo menos um item") List<OrderItemInsertDTO> items
        ) {
}
