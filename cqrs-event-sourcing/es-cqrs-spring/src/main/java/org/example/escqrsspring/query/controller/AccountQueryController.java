package org.example.escqrsspring.query.controller;


import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.example.escqrsspring.query.dtos.AccountStatementResponseDTO;
import org.example.escqrsspring.query.entities.Account;
import org.example.escqrsspring.query.entities.Operation;
import org.example.escqrsspring.query.queries.GetAccountStatementQuery;
import org.example.escqrsspring.query.queries.GetAllAccountQuery;
import org.example.escqrsspring.query.queries.WatchEventQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/queries/accounts")
public class AccountQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/all")
    public CompletableFuture<List<Account>> getAccounts(){

       var response =  queryGateway.query(new GetAllAccountQuery() , ResponseTypes.multipleInstancesOf(Account.class));
       return response;
    }
    @GetMapping("/statement/{accountId}")
    public CompletableFuture<AccountStatementResponseDTO> getAccountStatement(@PathVariable String accountId){

        return  queryGateway.query(new GetAccountStatementQuery(accountId) , ResponseTypes.instanceOf(AccountStatementResponseDTO.class));

    }


    @GetMapping(value = "/watch/{accountId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Operation> watch(@PathVariable String accountId){
        SubscriptionQueryResult<Operation, Operation> result = queryGateway.subscriptionQuery(new WatchEventQuery(accountId),
                ResponseTypes.instanceOf(Operation.class),
                ResponseTypes.instanceOf(Operation.class)
        );
        return result.initialResult().concatWith(result.updates());
    }


}
