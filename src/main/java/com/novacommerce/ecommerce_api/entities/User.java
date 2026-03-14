package com.novacommerce.ecommerce_api.entities;

import com.novacommerce.ecommerce_api.dtos.UserInsertDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private LocalDate birthDate;

    private String password;

    public User(UserInsertDTO userInsertDTO) {
        this.name = userInsertDTO.name();
        this.email = userInsertDTO.email();
        this.phone = userInsertDTO.phone();
        this.birthDate = userInsertDTO.birthDate();
        this.password = userInsertDTO.password();
    }
}
