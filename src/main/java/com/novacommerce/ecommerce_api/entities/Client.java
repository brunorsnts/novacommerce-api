package com.novacommerce.ecommerce_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_clients")
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @CPF
    @Column(nullable = false, unique = true)
    private String cpf;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "client")
    private Cart cart;

    public Client() {}

    public Client(Long id, String name, String email, String cpf, Cart cart) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.cart = cart;
    }
}
