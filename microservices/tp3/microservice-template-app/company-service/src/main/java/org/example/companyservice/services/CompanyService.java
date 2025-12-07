package org.example.companyservice.services;

import org.example.companyservice.dtos.CompanyRequestDTO;
import org.example.companyservice.dtos.CompanyResponseDTO;
import org.example.companyservice.entities.Company;

import java.util.List;

public interface CompanyService {
    CompanyResponseDTO saveCompany(CompanyRequestDTO companyRequestDTO);
    CompanyResponseDTO getCompany(String id);
    List<CompanyResponseDTO> getAllCompanies();
    List<CompanyResponseDTO> getCompaniesBySector(String sector);
    CompanyResponseDTO updateCompany(String id, CompanyRequestDTO companyRequestDTO);
    void deleteCompany(String id);
    CompanyResponseDTO updateStockPrice(String id, Double newPrice);
}
