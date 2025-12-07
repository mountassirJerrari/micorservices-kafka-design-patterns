package org.example.billingservice.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.billingservice.enitites.dto.Customer;
import org.example.billingservice.enitites.dto.Product;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductItem {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Double number;
    private Double finalPrice;
    @ManyToOne
    private Bill bill;
    private String productId;
    @Transient
    private Product product;


}
