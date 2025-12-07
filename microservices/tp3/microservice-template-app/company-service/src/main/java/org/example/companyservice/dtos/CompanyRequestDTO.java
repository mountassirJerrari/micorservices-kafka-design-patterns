package org.example.companyservice.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CompanyRequestDTO {
    @NotBlank(message = "Company name is required")
    private String name;
    
    @NotNull(message = "IPO date is required")
    @PastOrPresent(message = "IPO date must be in the past or present")
    private LocalDate ipoDate;
    
    @NotNull(message = "Current stock price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Stock price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Stock price must have up to 10 digits before and 2 after decimal")
    private BigDecimal currentStockPrice;
    
    @NotBlank(message = "Sector is required")
    private String sector;
}
