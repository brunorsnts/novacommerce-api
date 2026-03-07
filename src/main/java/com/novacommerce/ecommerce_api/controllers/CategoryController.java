package com.novacommerce.ecommerce_api.controllers;

import com.novacommerce.ecommerce_api.dtos.CategoryDTO;
import com.novacommerce.ecommerce_api.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO categoryDTO, UriComponentsBuilder uriBuilder) {
        CategoryDTO dto = service.insert(categoryDTO);
        URI uri = uriBuilder.path("/categories/{id}")
                .buildAndExpand(dto.id())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<PagedModel<CategoryDTO>> findAll(
            @PageableDefault(size = 10, sort = "name")
            Pageable pageable) {
        Page<CategoryDTO> categories = service.findAll(pageable);
        PagedModel<CategoryDTO> page = new PagedModel<>(categories);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
}
