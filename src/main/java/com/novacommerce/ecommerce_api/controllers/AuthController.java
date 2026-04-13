package com.novacommerce.ecommerce_api.controllers;

import com.novacommerce.ecommerce_api.dtos.TokenDTO;
import com.novacommerce.ecommerce_api.dtos.UserLoginDTO;
import com.novacommerce.ecommerce_api.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid UserLoginDTO dto) {
        TokenDTO tokenDTO = service.login(dto);
        return ResponseEntity.ok(tokenDTO);
    }
}
