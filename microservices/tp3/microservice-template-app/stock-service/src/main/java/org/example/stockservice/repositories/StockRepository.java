package org.example.stockservice.repositories;

import org.example.stockservice.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface StockRepository extends JpaRepository<Stock, String> {
    List<Stock> findByCompanyId(String companyId);
    
    @Query("SELECT s FROM Stock s WHERE s.companyId = :companyId ORDER BY s.timestamp DESC LIMIT 1")
    Optional<Stock> findLatestByCompanyId(@Param("companyId") String companyId);
    
    @Query("SELECT s FROM Stock s WHERE s.companyId = :companyId AND s.timestamp BETWEEN :startDate AND :endDate ORDER BY s.timestamp")
    List<Stock> findByCompanyIdAndDateRange(
        @Param("companyId") String companyId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
}
