package com.novacommerce.ecommerce_api.config;

import com.novacommerce.ecommerce_api.entities.Category;
import com.novacommerce.ecommerce_api.entities.Product;
import com.novacommerce.ecommerce_api.entities.Stock;
import com.novacommerce.ecommerce_api.entities.User;
import com.novacommerce.ecommerce_api.repositories.CategoryRepository;
import com.novacommerce.ecommerce_api.repositories.ProductRepository;
import com.novacommerce.ecommerce_api.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
public class TestConfig implements CommandLineRunner {

    private  final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public TestConfig(CategoryRepository categoryRepository,
                      ProductRepository productRepository,
                      UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User bruno = new User(null, "Bruno Santos", "bruno@gmail.com", "21999999999", LocalDate.parse("2002-05-08"), "bruno123");
        User alex = new User(null, "Alex Rodrigues", "alex@gmail.com", "21999999999", LocalDate.parse("2000-08-27"), "alex123");
        userRepository.saveAll(List.of(bruno, alex));

        Category category1 = new Category(null, "Eletrônicos", new HashSet<>());
        Stock stock1 = new Stock(null,null , 10);
        Product product1 = new Product(null, "Iphone 14 Pro Max 256GB", "Um celular pra chamar de seu", new BigDecimal("10000.00"), category1, stock1);
        stock1.setProduct(product1);

        Category category2 = new Category(null, "Móveis", new HashSet<>());
        Stock stock2 = new Stock(null,null , 10);
        Product product2 = new Product(null, "Cama Casal Box", "Mais conforto para você", new BigDecimal("5000.00"), category2, stock2);
        stock2.setProduct(product2);

        categoryRepository.saveAll(Arrays.asList(category1, category2));
        productRepository.saveAll(Arrays.asList(product1, product2));
    }
}
