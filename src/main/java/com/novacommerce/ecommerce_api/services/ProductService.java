package com.novacommerce.ecommerce_api.services;

import com.novacommerce.ecommerce_api.dtos.ProductDTO;
import com.novacommerce.ecommerce_api.entities.Category;
import com.novacommerce.ecommerce_api.entities.Product;
import com.novacommerce.ecommerce_api.repositories.CategoryRepository;
import com.novacommerce.ecommerce_api.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository repository,
                          CategoryRepository categoryRepository) {
        this.productRepository = repository;
        this.categoryRepository = categoryRepository;
    }

    public ProductDTO insert(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.category().id()).orElseThrow(() -> {
            logger.warn("Não existe nenhuma categoria vinculada ao id: {}", productDTO.category().id());
            return new RuntimeException("Categoria de id " + productDTO.category().id() + " inexistente");
        });

        Product product = new Product(productDTO, category);
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    public Page<ProductDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductDTO::new);
    }

    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> {
            logger.warn("Tentativa de busca falhou. Produto não encontrado para o id: {}", id);
            return new RuntimeException("Produto não encontrado");
        });
        return new ProductDTO(product);
    }

    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = productRepository.getReferenceById(id);
            entity.setName(dto.name());
            entity.setDescription(dto.description());
            entity.setPrice(dto.price());

            Category category = categoryRepository.getReferenceById(dto.category().id());
            entity.setCategory(category);
            entity = productRepository.save(entity);
            return new ProductDTO(entity);

        } catch (EntityNotFoundException  e) {
            logger.warn("Produto ou Categoria informados não existem.");
            throw new RuntimeException("Por favor verifique o id do produto ou categoria informado e tente novamente!");
        }
    }

    private boolean existsId(Long id) {
        return productRepository.existsById(id);
    }
}
