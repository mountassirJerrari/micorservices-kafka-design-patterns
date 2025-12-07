package org.example.stockservice.services.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.stockservice.clients.CompanyServiceClient;
import org.example.stockservice.dtos.StockRequestDTO;
import org.example.stockservice.dtos.StockResponseDTO;
import org.example.stockservice.entities.Stock;
import org.example.stockservice.exceptions.StockNotFoundException;
import org.example.stockservice.mappers.StockMapper;
import org.example.stockservice.repositories.StockRepository;
import org.example.stockservice.services.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final CompanyServiceClient companyServiceClient;

    @Override
    @CircuitBreaker(name = "stockService", fallbackMethod = "saveStockFallback")
    @Retry(name = "stockService")
    public StockResponseDTO saveStock(StockRequestDTO stockRequestDTO) {
        log.info("Saving new stock for company: {}", stockRequestDTO.getCompanyId());
        
        // Validate that the company exists
        companyServiceClient.getCompany(stockRequestDTO.getCompanyId());
        
        Stock stock = stockMapper.toEntity(stockRequestDTO);
        stock = stockRepository.save(stock);
        
        // Update company's current stock price
        updateCompanyCurrentPrice(stock.getCompanyId(), stock.getCloseValue().doubleValue());
        
        return stockMapper.toResponseDTO(stock);
    }

    @Override
    @Transactional(readOnly = true)
    public StockResponseDTO getStock(String id) {
        log.info("Fetching stock with id: {}", id);
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException("Stock not found with id: " + id));
        return stockMapper.toResponseDTO(stock);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockResponseDTO> getAllStocks() {
        log.info("Fetching all stocks");
        return stockRepository.findAll().stream()
                .map(stockMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockResponseDTO> getStocksByCompanyId(String companyId) {
        log.info("Fetching all stocks for company: {}", companyId);
        return stockRepository.findByCompanyId(companyId).stream()
                .map(stockMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StockResponseDTO getLatestStockByCompanyId(String companyId) {
        log.info("Fetching latest stock for company: {}", companyId);
        return stockRepository.findLatestByCompanyId(companyId)
                .map(stockMapper::toResponseDTO)
                .orElseThrow(() -> new StockNotFoundException("No stock found for company: " + companyId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockResponseDTO> getStocksByCompanyAndDateRange(String companyId, LocalDateTime startDate, LocalDateTime endDate) {
        log.info("Fetching stocks for company {} between {} and {}", companyId, startDate, endDate);
        return stockRepository.findByCompanyIdAndDateRange(companyId, startDate, endDate).stream()
                .map(stockMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @CircuitBreaker(name = "stockService", fallbackMethod = "updateStockFallback")
    @Retry(name = "stockService")
    public StockResponseDTO updateStock(String id, StockRequestDTO stockRequestDTO) {
        log.info("Updating stock with id: {}", id);
        
        Stock existingStock = stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException("Stock not found with id: " + id));
        
        String originalCompanyId = existingStock.getCompanyId();
        String newCompanyId = stockRequestDTO.getCompanyId();
        
        // If company ID is being changed, validate the new company exists
        if (!originalCompanyId.equals(newCompanyId)) {
            companyServiceClient.getCompany(newCompanyId);
        }
        
        stockMapper.updateEntityFromDTO(stockRequestDTO, existingStock);
        Stock updatedStock = stockRepository.save(existingStock);
        
        // Update company's current stock price if this is the latest stock
        if (stockRepository.findLatestByCompanyId(updatedStock.getCompanyId())
                .map(Stock::getId)
                .map(id::equals)
                .orElse(false)) {
            updateCompanyCurrentPrice(updatedStock.getCompanyId(), updatedStock.getCloseValue().doubleValue());
        }
        
        return stockMapper.toResponseDTO(updatedStock);
    }

    @Override
    @CircuitBreaker(name = "stockService", fallbackMethod = "deleteStockFallback")
    @Retry(name = "stockService")
    public void deleteStock(String id) {
        log.info("Deleting stock with id: {}", id);
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException("Stock not found with id: " + id));
        
        String companyId = stock.getCompanyId();
        stockRepository.delete(stock);
        
        // Update company's current stock price to the previous stock's close value if this was the latest stock
        stockRepository.findLatestByCompanyId(companyId).ifPresent(latestStock -> 
            updateCompanyCurrentPrice(companyId, latestStock.getCloseValue().doubleValue())
        );
    }

    @Override
    @CircuitBreaker(name = "companyService", fallbackMethod = "updateCompanyPriceFallback")
    @Retry(name = "companyService")
    public void updateCompanyCurrentPrice(String companyId, Double newPrice) {
        log.info("Updating current price for company: {} to {}", companyId, newPrice);
        companyServiceClient.updateStockPrice(companyId, newPrice);
    }

    // Fallback methods
    private StockResponseDTO saveStockFallback(StockRequestDTO dto, Throwable t) {
        log.error("Failed to save stock. Fallback method called. Error: {}", t.getMessage());
        throw new RuntimeException("Failed to save stock. Please try again later.");
    }

    private StockResponseDTO updateStockFallback(String id, StockRequestDTO dto, Throwable t) {
        log.error("Failed to update stock {}. Fallback method called. Error: {}", id, t.getMessage());
        throw new RuntimeException("Failed to update stock. Please try again later.");
    }

    private void deleteStockFallback(String id, Throwable t) {
        log.error("Failed to delete stock {}. Fallback method called. Error: {}", id, t.getMessage());
        throw new RuntimeException("Failed to delete stock. Please try again later.");
    }

    private void updateCompanyPriceFallback(String companyId, Double newPrice, Throwable t) {
        log.error("Failed to update company {} price. Fallback method called. Error: {}", companyId, t.getMessage());
        // In a real scenario, you might want to implement a retry mechanism or publish to a dead letter queue
    }
}
