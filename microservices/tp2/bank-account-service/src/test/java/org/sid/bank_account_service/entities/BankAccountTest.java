package org.sid.bank_account_service.entities;

import org.junit.jupiter.api.Test;
import org.sid.bank_account_service.enums.AccountType;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class BankAccountTest {
    @Autowired
    private BankAccountRepository bankAccountRepository ;
    @Test
    void testBankAccountCreation(){
        BankAccount bankAccount = BankAccount.builder()
                .balance(10000.0)
                .createdAt(new Date())
                .type(AccountType.SAVING_ACCOUNT)
                .currency("USD")
                .build();
        BankAccount savedAccount = bankAccountRepository.save(bankAccount);


        assertThat(savedAccount.getId()).isNotNull();
        assertThat(savedAccount.getType()).isEqualTo(AccountType.SAVING_ACCOUNT);

        Optional<BankAccount>  fetchedBankAccount = bankAccountRepository.findById(savedAccount.getId());

        assertThat(fetchedBankAccount).isPresent();
        assertThat(fetchedBankAccount.get().getCurrency()).isEqualTo("USD");






    }




}
