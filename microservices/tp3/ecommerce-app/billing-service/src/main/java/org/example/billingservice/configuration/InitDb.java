package org.example.billingservice.configuration;

import org.example.billingservice.enitites.Bill;
import org.example.billingservice.enitites.ProductItem;
import org.example.billingservice.repositories.BillRepository;
import org.example.billingservice.repositories.ProductItemRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;


@Configuration
public class InitDb {

    @Bean
    CommandLineRunner initDatabase(BillRepository billRepository , ProductItemRepository productItemRepository) {
        return args -> {

            for (int j = 0; j <7 ; j++) {
                var  bill  = Bill.builder()
                        .date(new Date())
                        .build();

                billRepository.save(bill);




            for (int i = 0; i <7 ; i++) {
                var  productItem  = ProductItem.builder()
                        .number(i+2d)
                        .finalPrice(i*5d)
                        .bill(bill)
                        .build();


                productItemRepository.save(productItem);


            }

            }
        };
   }
}

