package org.example.escqrsspring.query.repositories;

import org.example.escqrsspring.query.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Long> {

    List<Operation> findByAccountId(String id);
}
