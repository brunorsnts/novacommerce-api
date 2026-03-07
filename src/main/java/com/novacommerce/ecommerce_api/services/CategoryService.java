package com.novacommerce.ecommerce_api.services;

import com.novacommerce.ecommerce_api.entities.Category;
import com.novacommerce.ecommerce_api.repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Page<Category> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Category findById(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> {
            logger.warn("Tentativa de busca falhou. Categoria não encontrada para o id: {}", id);
            return new RuntimeException("Categoria não encontrada");
        });
        return category;
    }
}
