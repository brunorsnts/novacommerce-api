package com.novacommerce.ecommerce_api.controllers;

import com.novacommerce.ecommerce_api.dtos.OrderDTO;
import com.novacommerce.ecommerce_api.dtos.OrderInsertDTO;
import com.novacommerce.ecommerce_api.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        OrderDTO orderDTO = service.findById(id);
        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderInsertDTO dto, UriComponentsBuilder componentsBuilder) {
        OrderDTO orderDTO = service.insert(dto);
        URI uri = componentsBuilder.path("/orders/{id}")
                .buildAndExpand(orderDTO.id())
                .toUri();
        return ResponseEntity.created(uri).body(orderDTO);
    }
}
