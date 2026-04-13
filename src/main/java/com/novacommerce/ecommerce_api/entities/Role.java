package com.novacommerce.ecommerce_api.entities;

import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tb_roles")
public class Role implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    public Role() {
    }

    public Role(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    @Override
    public @Nullable String getAuthority() {
        return authority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
