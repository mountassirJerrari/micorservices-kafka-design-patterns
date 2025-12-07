package org.sid.bank_account_service.services;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.enums.AccountType;
import org.springframework.stereotype.Service;

@Service
public interface BankAccountService {

    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
