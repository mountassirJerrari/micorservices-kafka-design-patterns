package org.example.escqrsspring.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.escqrsspring.commons.enums.AccountStatus.AccountStatus;
@Getter @AllArgsConstructor
public class AccountCreatedEvent {

    private String accountID;
    private double initialBalance;
    private String currency;
    private AccountStatus status;

}
