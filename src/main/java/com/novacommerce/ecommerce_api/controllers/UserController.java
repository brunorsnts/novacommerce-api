package com.novacommerce.ecommerce_api.controllers;

import com.novacommerce.ecommerce_api.dtos.UserDTO;
import com.novacommerce.ecommerce_api.dtos.UserInsertDTO;
import com.novacommerce.ecommerce_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserInsertDTO userInsertDTO, UriComponentsBuilder uriBuilder) {
        UserDTO userDTO = service.insert(userInsertDTO);
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(userDTO.id()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }
}
