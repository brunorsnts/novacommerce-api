package com.novacommerce.ecommerce_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novacommerce.ecommerce_api.dtos.CategoryDTO;
import com.novacommerce.ecommerce_api.dtos.ProductDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @Positive(message = "Permitido apenas valores positivo")
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Stock stock;

    public Product(ProductDTO productDTO, Category category) {
        this.name = productDTO.name();
        this.description = productDTO.description();
        this.price = productDTO.price();
        this.category = category;
    }
}
