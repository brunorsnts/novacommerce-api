package com.novacommerce.ecommerce_api.services;

import com.novacommerce.ecommerce_api.dtos.OrderDTO;
import com.novacommerce.ecommerce_api.entities.Order;
import com.novacommerce.ecommerce_api.exceptions.ResourceNotFoundException;
import com.novacommerce.ecommerce_api.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado!"));
        return new OrderDTO(order);
    }
}
