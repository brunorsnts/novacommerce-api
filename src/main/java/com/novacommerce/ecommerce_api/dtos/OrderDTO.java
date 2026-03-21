package com.novacommerce.ecommerce_api.dtos;

import com.novacommerce.ecommerce_api.entities.Order;
import com.novacommerce.ecommerce_api.entities.enums.OrderStatus;

import java.time.Instant;
import java.util.List;

public record OrderDTO(Long id,
                       Instant moment,
                       OrderStatus status,
                       ClientDTO client,
                       List<OrderItemDTO> items) {

    public OrderDTO(Order order) {
        this(order.getId(), order.getMoment(), order.getStatus(), new ClientDTO(order.getClient()), order.getOrderItems().stream().map(OrderItemDTO::new).toList());
    }
}
