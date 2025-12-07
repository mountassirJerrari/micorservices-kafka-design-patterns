package org.example.companyservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.companyservice.dtos.CompanyRequestDTO;
import org.example.companyservice.dtos.CompanyResponseDTO;
import org.example.companyservice.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> createCompany(@Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
        log.info("Received request to create company: {}", companyRequestDTO.getName());
        CompanyResponseDTO createdCompany = companyService.saveCompany(companyRequestDTO);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> getCompany(@PathVariable String id) {
        log.info("Fetching company with id: {}", id);
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDTO>> getAllCompanies() {
        log.info("Fetching all companies");
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/sector/{sector}")
    public ResponseEntity<List<CompanyResponseDTO>> getCompaniesBySector(@PathVariable String sector) {
        log.info("Fetching companies in sector: {}", sector);
        return ResponseEntity.ok(companyService.getCompaniesBySector(sector));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> updateCompany(
            @PathVariable String id, 
            @Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
        log.info("Updating company with id: {}", id);
        return ResponseEntity.ok(companyService.updateCompany(id, companyRequestDTO));
    }

    @PatchMapping("/{id}/stock-price/{price}")
    public ResponseEntity<CompanyResponseDTO> updateStockPrice(
            @PathVariable String id, 
            @PathVariable Double price) {
        log.info("Updating stock price for company id: {} to {}", id, price);
        return ResponseEntity.ok(companyService.updateStockPrice(id, price));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String id) {
        log.info("Deleting company with id: {}", id);
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
