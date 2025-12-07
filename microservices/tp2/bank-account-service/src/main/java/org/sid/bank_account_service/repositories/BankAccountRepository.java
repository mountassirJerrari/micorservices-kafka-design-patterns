package org.sid.bank_account_service.repositories;

import org.sid.bank_account_service.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
