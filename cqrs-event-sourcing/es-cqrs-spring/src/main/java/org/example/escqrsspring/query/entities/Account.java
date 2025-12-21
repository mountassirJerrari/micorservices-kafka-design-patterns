package org.example.escqrsspring.query.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.example.escqrsspring.commons.enums.AccountStatus.AccountStatus;

import java.time.Instant;
import java.util.Date;


@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Account {

    @Id
    private String id;
    private double balance ;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private String currency;
    private Instant CreatedAt ;
}
