package com.novacommerce.ecommerce_api.repositories;

import com.novacommerce.ecommerce_api.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
