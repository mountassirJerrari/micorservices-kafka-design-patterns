package org.sid.bank_account_service.web;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/bankAccount")
public class AccountRestController {

    private BankAccountRepository bankAccountRepository ;

    private BankAccountService bankAccountService ;

    public AccountRestController(BankAccountRepository bankAccountRepository1 , BankAccountService bankAccountService){
        this.bankAccountRepository = bankAccountRepository1 ;
        this.bankAccountService = bankAccountService ;
    }
    @GetMapping
    public List<BankAccount> getBankAccounts()
    {
        return bankAccountRepository.findAll();
    }
    @GetMapping("/{id}")
    public BankAccount getBankAccount(@PathVariable  String id ){
        return bankAccountRepository.findById(id).orElseThrow(()->  new RuntimeException("account not found"));
    }
    @PostMapping
    public BankAccountResponseDTO saveBankAccount(@RequestBody BankAccountRequestDTO bankAccount ){

        return bankAccountService.addAccount(bankAccount);
    }
    @PutMapping("/{id}")
    public BankAccount saveBankAccount(@PathVariable String id ,@RequestBody BankAccount bankAccount ){


        BankAccount account = bankAccountRepository.findById(id).orElseThrow(()->  new RuntimeException("account not found"));
        if (bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        if (bankAccount.getCreatedAt()!=null)account.setCreatedAt(new Date());
        if (bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if (bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        return bankAccountRepository.save(account);
    }
    @DeleteMapping("/{id}")
    public void deleteBankAccount(@PathVariable String id  ){

       bankAccountRepository.deleteById(id);
    }
}
