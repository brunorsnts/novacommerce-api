package com.novacommerce.ecommerce_api.services;

import com.novacommerce.ecommerce_api.entities.Product;
import com.novacommerce.ecommerce_api.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Product findById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> {
            logger.warn("Tentativa de busca falhou. Produto não encontrado para o id: {}", id);
            return new RuntimeException("Produto não encontrado");
        });
        return product;
    }
}
