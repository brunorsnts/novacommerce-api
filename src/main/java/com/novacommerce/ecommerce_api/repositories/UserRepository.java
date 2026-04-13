package com.novacommerce.ecommerce_api.repositories;

import com.novacommerce.ecommerce_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
