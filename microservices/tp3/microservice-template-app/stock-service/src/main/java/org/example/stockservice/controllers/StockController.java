package org.example.stockservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.stockservice.dtos.StockRequestDTO;
import org.example.stockservice.dtos.StockResponseDTO;
import org.example.stockservice.services.StockService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<StockResponseDTO> createStock(@Valid @RequestBody StockRequestDTO stockRequestDTO) {
        log.info("Received request to create stock for company: {}", stockRequestDTO.getCompanyId());
        StockResponseDTO createdStock = stockService.saveStock(stockRequestDTO);
        return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDTO> getStock(@PathVariable String id) {
        log.info("Fetching stock with id: {}", id);
        return ResponseEntity.ok(stockService.getStock(id));
    }

    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> getAllStocks() {
        log.info("Fetching all stocks");
        return ResponseEntity.ok(stockService.getAllStocks());
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<StockResponseDTO>> getStocksByCompanyId(
            @PathVariable String companyId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        
        if (startDate != null && endDate != null) {
            log.info("Fetching stocks for company: {} between {} and {}", companyId, startDate, endDate);
            return ResponseEntity.ok(stockService.getStocksByCompanyAndDateRange(companyId, startDate, endDate));
        } else {
            log.info("Fetching all stocks for company: {}", companyId);
            return ResponseEntity.ok(stockService.getStocksByCompanyId(companyId));
        }
    }

    @GetMapping("/company/{companyId}/latest")
    public ResponseEntity<StockResponseDTO> getLatestStockByCompanyId(@PathVariable String companyId) {
        log.info("Fetching latest stock for company: {}", companyId);
        return ResponseEntity.ok(stockService.getLatestStockByCompanyId(companyId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockResponseDTO> updateStock(
            @PathVariable String id,
            @Valid @RequestBody StockRequestDTO stockRequestDTO) {
        log.info("Updating stock with id: {}", id);
        return ResponseEntity.ok(stockService.updateStock(id, stockRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable String id) {
        log.info("Deleting stock with id: {}", id);
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
