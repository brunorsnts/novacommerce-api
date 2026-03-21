package com.novacommerce.ecommerce_api.repositories;

import com.novacommerce.ecommerce_api.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
