package org.example.customerservice.configuration;

import org.example.customerservice.entities.Customer;
import org.example.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;


@Configuration
public class InitDb {

    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepository) {
        return args -> {

            for (int i = 0; i <7 ; i++) {
                var  custommer  = Customer.builder().email("unknown@gmail.com").name("Customer "+i).build();

                customerRepository.save(custommer);

            }
        };
   }
}

