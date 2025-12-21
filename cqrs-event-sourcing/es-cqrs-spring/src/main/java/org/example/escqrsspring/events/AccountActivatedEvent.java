package org.example.escqrsspring.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.escqrsspring.commons.enums.AccountStatus.AccountStatus;

@Getter @AllArgsConstructor
public class AccountActivatedEvent {

    private String accountID;

    private AccountStatus status;

}
