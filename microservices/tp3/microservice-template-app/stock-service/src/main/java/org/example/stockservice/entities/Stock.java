package org.example.stockservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false)
    private String companyId;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal openValue;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal highValue;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal lowValue;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal closeValue;
    
    @Column(nullable = false)
    private Long volume;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
}
