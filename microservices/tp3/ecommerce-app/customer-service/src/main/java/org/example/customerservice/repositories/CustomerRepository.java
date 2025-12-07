package org.example.customerservice.repositories;

import org.example.customerservice.entities.Customer;
import org.example.customerservice.entities.projections.CustomerProjection1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RestResource
public interface CustomerRepository extends JpaRepository<Customer , String> {

}
