package org.example.escqrsspring.command.aggregates;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.example.escqrsspring.command.controllers.commands.AddAccountCommand;
import org.example.escqrsspring.command.controllers.commands.CreditAccountCommand;
import org.example.escqrsspring.commons.enums.AccountStatus.AccountStatus;
import org.example.escqrsspring.events.AccountActivatedEvent;
import org.example.escqrsspring.events.AccountCreatedEvent;
import org.example.escqrsspring.events.AccountCreditedEvent;

@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

    public AccountAggregate(){}

    @CommandHandler
    public AccountAggregate(AddAccountCommand command){

        log.info("###################### add account command  received ################### ");
        if ( command.getInitialBalance() <0 ) throw new  IllegalArgumentException(" balance should be greater or equal than 0");
        AggregateLifecycle.apply(new AccountCreatedEvent(command.getId(),
                command.getInitialBalance(),
                command.getCurrency(),
                AccountStatus.CREATED));

        AggregateLifecycle.apply(new AccountActivatedEvent(command.getId(),
                AccountStatus.ACTIVATED));


    }

    @CommandHandler
    public void handle(CreditAccountCommand command){

        log.info("###################### credit account command  received ################### ");

        if (!this.status.equals(AccountStatus.ACTIVATED)) throw new RuntimeException(" can t credit an uncreated account with the account id "+ this.accountId);
        if( command.getAmount()<=0 ) throw new RuntimeException(" the ammount to credit should be grater than 0");
        AggregateLifecycle.apply(new AccountCreditedEvent(command.getId(),
                command.getAmount(),
                command.getCurrency()));




    }



    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent){
        log.info("###################### account event creation received ################### ");
        this.accountId = accountCreatedEvent.getAccountID();
        this.balance = accountCreatedEvent.getInitialBalance();
        this.status = accountCreatedEvent.getStatus();
        this.currency = accountCreatedEvent.getCurrency();
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent accountActivatedEvent){
        log.info("###################### account has been activated  ################### ");
        this.status = accountActivatedEvent.getStatus();
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent accountCreditedEvent){
        log.info("###################### account event creation received ################### ");
        this.balance = this.balance + accountCreditedEvent.getAmount();
    }

}
