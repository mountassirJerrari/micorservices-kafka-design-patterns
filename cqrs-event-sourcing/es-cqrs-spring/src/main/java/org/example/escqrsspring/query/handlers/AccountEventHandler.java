package org.example.escqrsspring.query.handlers;


import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.example.escqrsspring.events.AccountActivatedEvent;
import org.example.escqrsspring.events.AccountCreatedEvent;
import org.example.escqrsspring.events.AccountCreditedEvent;
import org.example.escqrsspring.query.entities.Account;
import org.example.escqrsspring.query.entities.Operation;
import org.example.escqrsspring.query.entities.OperationType;
import org.example.escqrsspring.query.repositories.AccountRepository;
import org.example.escqrsspring.query.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountEventHandler {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private QueryUpdateEmitter queryUpdateEmitter;

    @EventHandler
    public void on (AccountCreatedEvent event, EventMessage eventMessage){
        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% query account created %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        var account  = Account.builder()
                .id(event.getAccountID())
                .balance(event.getInitialBalance())
                .currency(event.getCurrency())
                .status(event.getStatus())
                .CreatedAt(eventMessage.getTimestamp()).build();

        accountRepository.save(account);

    }
    @EventHandler
    public void on (AccountActivatedEvent event){
        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%% query activation account created %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        var account  = accountRepository.findById(event.getAccountID()).orElseThrow();
        account.setStatus(event.getStatus());

        accountRepository.save(account);

    }
    @EventHandler
    public void on (AccountCreditedEvent event, EventMessage eventMessage){
        log.info("################# AccountCreditedEvent ################");
        Account account = accountRepository.findById(event.getAccountID()).get();
        Operation operation = Operation.builder()
                .date(eventMessage.getTimestamp())
                .amount(event.getAmount())
                .type(OperationType.CREDIT)
                .account(account)
                .build();
        Operation savedOperation = operationRepository.save(operation);
        account.setBalance(account.getBalance()+operation.getAmount());
        accountRepository.save(account);

        queryUpdateEmitter.emit(e->true ,savedOperation );
    }

}
