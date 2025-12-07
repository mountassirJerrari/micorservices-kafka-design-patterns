package org.example.companyservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.companyservice.dtos.CompanyRequestDTO;
import org.example.companyservice.dtos.CompanyResponseDTO;
import org.example.companyservice.entities.Company;
import org.example.companyservice.exceptions.CompanyNotFoundException;
import org.example.companyservice.mappers.CompanyMapper;
import org.example.companyservice.repositories.CompanyRepository;
import org.example.companyservice.services.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponseDTO saveCompany(CompanyRequestDTO companyRequestDTO) {
        log.info("Saving new company: {}", companyRequestDTO.getName());
        
        if (companyRepository.existsByName(companyRequestDTO.getName())) {
            throw new RuntimeException("Company with name " + companyRequestDTO.getName() + " already exists");
        }
        
        Company company = companyMapper.toEntity(companyRequestDTO);
        company = companyRepository.save(company);
        return companyMapper.toResponseDTO(company);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponseDTO getCompany(String id) {
        log.info("Fetching company with id: {}", id);
        Company company = findCompanyById(id);
        return companyMapper.toResponseDTO(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> getAllCompanies() {
        log.info("Fetching all companies");
        return companyRepository.findAll().stream()
                .map(companyMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> getCompaniesBySector(String sector) {
        log.info("Fetching companies in sector: {}", sector);
        return companyRepository.findBySector(sector).stream()
                .map(companyMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyResponseDTO updateCompany(String id, CompanyRequestDTO companyRequestDTO) {
        log.info("Updating company with id: {}", id);
        Company existingCompany = findCompanyById(id);
        companyMapper.updateEntityFromDTO(companyRequestDTO, existingCompany);
        Company updatedCompany = companyRepository.save(existingCompany);
        return companyMapper.toResponseDTO(updatedCompany);
    }

    @Override
    public void deleteCompany(String id) {
        log.info("Deleting company with id: {}", id);
        if (!companyRepository.existsById(id)) {
            throw new CompanyNotFoundException("Company not found with id: " + id);
        }
        companyRepository.deleteById(id);
    }

    @Override
    public CompanyResponseDTO updateStockPrice(String id, Double newPrice) {
        log.info("Updating stock price for company id: {} to {}", id, newPrice);
        Company company = findCompanyById(id);
        company.setCurrentStockPrice(BigDecimal.valueOf(newPrice));
        Company updatedCompany = companyRepository.save(company);
        return companyMapper.toResponseDTO(updatedCompany);
    }

    private Company findCompanyById(String id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + id));
    }
}
