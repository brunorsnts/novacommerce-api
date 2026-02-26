package com.novacommerce.ecommerce_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Order {

    @Id
    private Long id;
}
