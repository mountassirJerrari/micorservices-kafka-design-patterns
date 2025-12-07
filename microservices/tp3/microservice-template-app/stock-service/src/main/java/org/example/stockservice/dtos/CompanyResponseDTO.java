package org.example.stockservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String website;
}
