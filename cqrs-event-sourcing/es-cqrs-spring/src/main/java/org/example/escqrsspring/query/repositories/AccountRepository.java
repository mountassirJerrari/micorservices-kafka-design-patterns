package org.example.escqrsspring.query.repositories;

import org.example.escqrsspring.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
