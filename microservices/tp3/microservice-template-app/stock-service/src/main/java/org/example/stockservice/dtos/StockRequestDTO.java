package org.example.stockservice.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StockRequestDTO {
    @NotNull(message = "Company ID is required")
    private String companyId;
    
    @NotNull(message = "Open value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Open value must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Open value must have up to 10 digits before and 2 after decimal")
    private BigDecimal openValue;
    
    @NotNull(message = "High value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "High value must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "High value must have up to 10 digits before and 2 after decimal")
    private BigDecimal highValue;
    
    @NotNull(message = "Low value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Low value must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Low value must have up to 10 digits before and 2 after decimal")
    private BigDecimal lowValue;
    
    @NotNull(message = "Close value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Close value must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Close value must have up to 10 digits before and 2 after decimal")
    private BigDecimal closeValue;
    
    @NotNull(message = "Volume is required")
    @DecimalMin(value = "0", inclusive = false, message = "Volume must be greater than 0")
    private Long volume;
}
