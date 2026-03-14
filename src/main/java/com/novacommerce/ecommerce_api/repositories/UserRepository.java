package com.novacommerce.ecommerce_api.repositories;

import com.novacommerce.ecommerce_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
