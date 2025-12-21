package org.example.escqrsspring.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.escqrsspring.commons.enums.AccountStatus.AccountStatus;
@Getter @AllArgsConstructor
public class AccountCreditedEvent {

    private String accountID;
    private double amount;
    private String currency;

}
