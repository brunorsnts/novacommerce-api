package com.novacommerce.ecommerce_api.dtos;

import com.novacommerce.ecommerce_api.entities.OrderItem;

import java.math.BigDecimal;

public record OrderItemDTO(
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal price) {

    public OrderItemDTO(OrderItem item) {
        this(item.getProduct().getId(), item.getProduct().getName(), item.getQuantity(), item.getPrice());
    }

    public BigDecimal getSubTotal() {
        return price.multiply(new BigDecimal(quantity));
    }
}
