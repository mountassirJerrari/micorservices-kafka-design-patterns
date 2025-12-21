package org.example.escqrsspring.command.controllers;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.example.escqrsspring.command.controllers.commands.AddAccountCommand;
import org.example.escqrsspring.command.controllers.commands.CreditAccountCommand;
import org.example.escqrsspring.command.dtos.AddNewAccountRequestDTO;
import org.example.escqrsspring.command.dtos.CreditAccountRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/accounts")
public class AccountCommandController {

    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private EventStore eventStore;
    @PostMapping("/add")
    public  CompletableFuture<String> addNewAccount(@RequestBody AddNewAccountRequestDTO requestDTO){
        CompletableFuture<String> response  = commandGateway.send(new AddAccountCommand(UUID.randomUUID().toString(), requestDTO.initialBalance(), requestDTO.currency()));

        return response;
    }

    @PostMapping("/credit")
    public  CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO requestDTO){
        CompletableFuture<String> response  = commandGateway.send(new CreditAccountCommand(requestDTO.accountId(), requestDTO.amount(), requestDTO.currency()));

        return response;
    }
    @ExceptionHandler
    public String exceptionHandler(Exception exception){
        return exception.getMessage();
    }

    @GetMapping("events/{id}")
    public Stream eventStore(@PathVariable String id){

        return eventStore.readEvents(id).asStream();
    }

}
