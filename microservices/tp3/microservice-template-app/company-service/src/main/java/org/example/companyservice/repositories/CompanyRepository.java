package org.example.companyservice.repositories;

import org.example.companyservice.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CompanyRepository extends JpaRepository<Company, String> {
    List<Company> findBySector(String sector);
    boolean existsByName(String name);
}
