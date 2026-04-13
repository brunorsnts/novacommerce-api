package com.novacommerce.ecommerce_api.services;

import com.novacommerce.ecommerce_api.config.TokenService;
import com.novacommerce.ecommerce_api.dtos.TokenDTO;
import com.novacommerce.ecommerce_api.dtos.UserLoginDTO;
import com.novacommerce.ecommerce_api.entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthService(AuthenticationManager authenticationManager,
                          TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public TokenDTO login(UserLoginDTO dto) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new TokenDTO(token);
    }
}
