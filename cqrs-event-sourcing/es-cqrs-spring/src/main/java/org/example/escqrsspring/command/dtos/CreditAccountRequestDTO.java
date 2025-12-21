package org.example.escqrsspring.command.dtos;

public record CreditAccountRequestDTO (String  accountId , double amount  , String currency) {
}
