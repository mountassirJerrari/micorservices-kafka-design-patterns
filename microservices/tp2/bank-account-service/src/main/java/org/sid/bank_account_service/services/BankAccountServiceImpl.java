package org.sid.bank_account_service.services;

import jakarta.transaction.Transactional;
import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Transactional
@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository ;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {

        BankAccount bankAccount = BankAccount.builder()
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .createdAt(new Date())
                .build();

        var savedBankAccount = bankAccountRepository.save(bankAccount);


        return new BankAccountResponseDTO(savedBankAccount.getId(), savedBankAccount.getCreatedAt(),savedBankAccount.getCurrency(),savedBankAccount.getType(),savedBankAccount.getBalance());
    }
}
