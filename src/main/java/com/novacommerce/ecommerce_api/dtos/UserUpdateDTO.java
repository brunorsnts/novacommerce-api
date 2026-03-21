package com.novacommerce.ecommerce_api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UserUpdateDTO(
        @NotBlank(message = "Campo obrigatório") String name,
        String phone,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate) {
}
