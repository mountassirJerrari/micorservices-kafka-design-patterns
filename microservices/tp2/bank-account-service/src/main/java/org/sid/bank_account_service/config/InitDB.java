package org.sid.bank_account_service.config;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.entities.Customer;
import org.sid.bank_account_service.enums.AccountType;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class InitDB {

    @Autowired
    private BankAccountRepository bankAccountRepository ;

    @Autowired
    private CustomerRepository customerRepository ;

    @Bean
    public CommandLineRunner insertAccount(){

        return  args ->{


                Customer c = Customer.builder()
                        .nom("snater")
                        .build();
                customerRepository.save(c);




            for (int i = 0; i <5 ; i++) {
                BankAccount account = BankAccount.builder()
                        .balance(i*500.0)
                        .currency("USD")
                        .type(i%2==0 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                        .createdAt(new Date())
                        .customer(c)
                        .build();
                 bankAccountRepository.save(account);

            }



        } ;
    }
}
