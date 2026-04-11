package com.novacommerce.ecommerce_api.config;

import com.novacommerce.ecommerce_api.entities.*;
import com.novacommerce.ecommerce_api.entities.enums.OrderStatus;
import com.novacommerce.ecommerce_api.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
public class TestConfig implements CommandLineRunner {

    private  final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public TestConfig(CategoryRepository categoryRepository,
                      ProductRepository productRepository,
                      UserRepository userRepository,
                      ClientRepository clientRepository,
                      OrderRepository orderRepository,
                      OrderItemRepository orderItemRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
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

        // Criando um Cliente
        Client client1 = new Client(null, "Maria Silva", "maria@gmail.com", "97003925036", null);
        clientRepository.save(client1);

        // Criando um Pedido para a Maria
        Order order1 = new Order(Instant.now(), OrderStatus.PAID, client1);
        orderRepository.save(order1);

        // Adicionando 2 iPhones no Pedido da Maria
        OrderItem orderItem1 = new OrderItem(null, 2, product1.getPrice(), order1, product1);
        orderItemRepository.save(orderItem1);
    }
}
