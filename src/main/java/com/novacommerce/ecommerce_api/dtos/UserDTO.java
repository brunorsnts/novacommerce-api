package com.novacommerce.ecommerce_api.dtos;

import com.novacommerce.ecommerce_api.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UserDTO(Long id,
                      String name,
                      String email,
                      String phone,
                      LocalDate birthDate) {

    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getBirthDate());
    }
}
