package org.example.stockservice.services;

import org.example.stockservice.dtos.StockRequestDTO;
import org.example.stockservice.dtos.StockResponseDTO;
import org.example.stockservice.entities.Stock;

import java.time.LocalDateTime;
import java.util.List;

public interface StockService {
    StockResponseDTO saveStock(StockRequestDTO stockRequestDTO);
    StockResponseDTO getStock(String id);
    List<StockResponseDTO> getAllStocks();
    List<StockResponseDTO> getStocksByCompanyId(String companyId);
    StockResponseDTO getLatestStockByCompanyId(String companyId);
    List<StockResponseDTO> getStocksByCompanyAndDateRange(String companyId, LocalDateTime startDate, LocalDateTime endDate);
    StockResponseDTO updateStock(String id, StockRequestDTO stockRequestDTO);
    void deleteStock(String id);
    void updateCompanyCurrentPrice(String companyId, Double newPrice);
}
