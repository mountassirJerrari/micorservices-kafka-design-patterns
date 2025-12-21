package org.example.escqrsspring.query.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private Instant date;
    private double amount;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    private  String currency;
    @ManyToOne
    private Account account;


}
