package org.example.escqrsspring.query.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.escqrsspring.query.entities.Account;
import org.example.escqrsspring.query.entities.Operation;

import java.util.List;

@Getter @Setter @AllArgsConstructor
public class AccountStatementResponseDTO {

    private  Account acount;
    private List<Operation> operations;
}
