package com.novacommerce.ecommerce_api.repositories;

import com.novacommerce.ecommerce_api.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
