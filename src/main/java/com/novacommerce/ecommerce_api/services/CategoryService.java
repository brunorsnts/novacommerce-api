package com.novacommerce.ecommerce_api.services;

import com.novacommerce.ecommerce_api.dtos.CategoryDTO;
import com.novacommerce.ecommerce_api.entities.Category;
import com.novacommerce.ecommerce_api.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public CategoryDTO insert(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO);
        return new CategoryDTO(repository.save(category));
    }

    public Page<CategoryDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(CategoryDTO::new);
    }

    public CategoryDTO findById(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> {
            logger.warn("Tentativa de busca falhou. Categoria não encontrada para o id: {}", id);
            return new RuntimeException("Categoria não encontrada");
        });
        return new CategoryDTO(category);
    }

    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {
            Category entity = repository.getReferenceById(id);
            entity.setName(dto.name());
            return new CategoryDTO(repository.save(entity));

        }catch (EntityNotFoundException e) {
            logger.warn("Falha na tentativa de atualização! Categoria não existe.");
            throw new RuntimeException("Categoria de id " + id + " não encontrada.");
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
