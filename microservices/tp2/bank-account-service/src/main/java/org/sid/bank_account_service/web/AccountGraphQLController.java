package org.sid.bank_account_service.web;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.services.BankAccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bankAccount")
public class AccountGraphQLController {

    private BankAccountRepository bankAccountRepository ;

    private BankAccountService bankAccountService ;

    public AccountGraphQLController(BankAccountRepository bankAccountRepository1 , BankAccountService bankAccountService){
        this.bankAccountRepository = bankAccountRepository1 ;
        this.bankAccountService = bankAccountService ;
    }

    @QueryMapping
    public List<BankAccount> accountsList()
    {

        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount accountById(@Argument String id)
    {

        return bankAccountRepository.findById(id).orElseThrow();
    }
    @MutationMapping
    public BankAccountResponseDTO createAccount(@Argument BankAccountRequestDTO bankAccount)
    {
        return bankAccountService.addAccount(bankAccount);
    }

}
