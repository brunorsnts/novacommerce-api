package com.novacommerce.ecommerce_api.dtos;

import com.novacommerce.ecommerce_api.entities.Client;

public record ClientDTO(Long id, String name) {

    public ClientDTO(Client client) {
        this(client.getId(), client.getName());
    }
}
