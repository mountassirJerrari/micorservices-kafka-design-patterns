package org.example.inventoryservice.config;

import org.example.inventoryservice.entities.Product;
import org.example.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDb {


    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {

        return args -> {

            for (int i = 0; i < 5 ; i++) {

                var product = Product.builder().name("Product " + i)
                        .price((i*10d+40d))
                        .quantity((40d))
                        .build();
                productRepository.save(product);

            }
        };
    }
}
