package com.novacommerce.ecommerce_api.controllers;

import com.novacommerce.ecommerce_api.entities.Category;
import com.novacommerce.ecommerce_api.repositories.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = repository.findAll();
        return ResponseEntity.ok(categories);
    }
}
