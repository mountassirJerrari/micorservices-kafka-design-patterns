package org.example.billingservice.enitites.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private String id;
    private String name;
    private Double price;
    private Double quantity;
}
