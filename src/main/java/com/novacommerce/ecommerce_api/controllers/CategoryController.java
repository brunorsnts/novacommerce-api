package com.novacommerce.ecommerce_api.controllers;

import com.novacommerce.ecommerce_api.entities.Category;
import com.novacommerce.ecommerce_api.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<PagedModel<Category>> findAll(
            @PageableDefault(size = 10, sort = "name")
            Pageable pageable) {
        Page<Category> categories = service.findAll(pageable);
        PagedModel<Category> page = new PagedModel<>(categories);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
}
