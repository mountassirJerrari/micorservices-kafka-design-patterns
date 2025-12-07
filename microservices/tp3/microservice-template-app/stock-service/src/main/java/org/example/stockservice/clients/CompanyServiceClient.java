package org.example.stockservice.clients;

import org.example.stockservice.dtos.CompanyResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "company-service", url = "${company.service.url}")
public interface CompanyServiceClient {
    
    @GetMapping("/api/companies/{id}")
    CompanyResponseDTO getCompany(@PathVariable String id);
    
    @PutMapping("/api/companies/{id}/stock-price/{price}")
    CompanyResponseDTO updateStockPrice(
        @PathVariable String id, 
        @PathVariable Double price
    );
}
