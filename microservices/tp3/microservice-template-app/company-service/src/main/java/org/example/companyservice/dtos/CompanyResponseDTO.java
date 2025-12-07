package org.example.companyservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CompanyResponseDTO {
    private String id;
    private String name;
    private LocalDate ipoDate;
    private BigDecimal currentStockPrice;
    private String sector;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
