package org.example.escqrsspring.query.handlers;


import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryHandler;
import org.example.escqrsspring.events.AccountActivatedEvent;
import org.example.escqrsspring.events.AccountCreatedEvent;
import org.example.escqrsspring.events.AccountCreditedEvent;
import org.example.escqrsspring.query.dtos.AccountStatementResponseDTO;
import org.example.escqrsspring.query.entities.Account;
import org.example.escqrsspring.query.entities.Operation;
import org.example.escqrsspring.query.entities.OperationType;
import org.example.escqrsspring.query.queries.GetAccountStatementQuery;
import org.example.escqrsspring.query.queries.GetAllAccountQuery;
import org.example.escqrsspring.query.queries.WatchEventQuery;
import org.example.escqrsspring.query.repositories.AccountRepository;
import org.example.escqrsspring.query.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountQueryHandler {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationRepository operationRepository;

    @QueryHandler
    public List<Account> on (GetAllAccountQuery event){
        return accountRepository.findAll();
    }

    @QueryHandler
    public AccountStatementResponseDTO on (GetAccountStatementQuery event){

        return  new AccountStatementResponseDTO(accountRepository.findById(event.getAccountId()).orElseThrow(),operationRepository.findByAccountId(event.getAccountId()));


    }
    @QueryHandler
    public Operation on(WatchEventQuery event){
        return Operation.builder().build();
    }



}
