package com.novacommerce.ecommerce_api.entities;

import com.novacommerce.ecommerce_api.dtos.UserInsertDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_users")
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private LocalDate birthDate;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String name, String email, String phone, LocalDate birthDate, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.password = password;
    }

    public User(UserInsertDTO userInsertDTO) {
        this.name = userInsertDTO.name();
        this.email = userInsertDTO.email();
        this.phone = userInsertDTO.phone();
        this.birthDate = userInsertDTO.birthDate();
        this.password = userInsertDTO.password();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
