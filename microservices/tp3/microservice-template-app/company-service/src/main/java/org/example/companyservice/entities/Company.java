package org.example.companyservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "companies")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(name = "ipo_date", nullable = false)
    private LocalDate ipoDate;
    
    @Column(name = "current_stock_price", precision = 10, scale = 2)
    private BigDecimal currentStockPrice;
    
    @Column(nullable = false)
    private String sector; // IT, AI, Banque, Assurance, etc.
    
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;
    
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }
}
