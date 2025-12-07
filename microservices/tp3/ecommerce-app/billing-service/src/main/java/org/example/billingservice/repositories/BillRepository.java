package org.example.billingservice.repositories;

import org.example.billingservice.enitites.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource()
public interface BillRepository extends JpaRepository<Bill, String> {

}
