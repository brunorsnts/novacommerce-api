package com.novacommerce.ecommerce_api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UserInsertDTO(@NotBlank (message = "Campo Requerido") String name,
                            @NotBlank (message = "Campo Requerido") @Email String email,
                            @NotBlank (message = "Campo Requerido") String phone,
                            @JsonFormat(pattern = "dd/MM/yyyy") LocalDate birthDate,
                            @NotBlank (message = "Campo Requerido") String password) {
}
