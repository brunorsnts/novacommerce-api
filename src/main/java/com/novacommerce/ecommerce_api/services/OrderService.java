package com.novacommerce.ecommerce_api.services;

import com.novacommerce.ecommerce_api.dtos.OrderDTO;
import com.novacommerce.ecommerce_api.dtos.OrderInsertDTO;
import com.novacommerce.ecommerce_api.entities.Client;
import com.novacommerce.ecommerce_api.entities.Order;
import com.novacommerce.ecommerce_api.entities.OrderItem;
import com.novacommerce.ecommerce_api.entities.Product;
import com.novacommerce.ecommerce_api.entities.enums.OrderStatus;
import com.novacommerce.ecommerce_api.exceptions.ResourceNotFoundException;
import com.novacommerce.ecommerce_api.repositories.ClientRepository;
import com.novacommerce.ecommerce_api.repositories.OrderItemRepository;
import com.novacommerce.ecommerce_api.repositories.OrderRepository;
import com.novacommerce.ecommerce_api.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository,
                        ClientRepository clientRepository,
                        ProductRepository productRepository,
                        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado!"));
        return new OrderDTO(order);
    }

    public OrderDTO insert(OrderInsertDTO dto) {
        try {
            Client client = clientRepository.getReferenceById(dto.clientId());
            Order order = new Order(Instant.now(), OrderStatus.WAITING_PAYMENT, client);
            order = orderRepository.save(order);

            Order finalOrder = order;

            List<OrderItem> orderItems = dto.items().stream()
                    .map(item -> {
                        Product product = productRepository.getReferenceById(item.productId());
                        return new OrderItem(null, item.quantity(), product.getPrice(), finalOrder, product);
                    }).toList();

            finalOrder.getOrderItems().addAll(orderItems);

            orderItemRepository.saveAll(finalOrder.getOrderItems());

            return new OrderDTO(finalOrder);

        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Cliente ou Produto não encontrado");
        }
    }
}
