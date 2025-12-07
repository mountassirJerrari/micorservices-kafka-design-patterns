package org.sid.bank_account_service.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.enums.AccountType;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountRestControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc ;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    void shouldReturnAllAccounts() throws Exception {

        BankAccount account = BankAccount.builder()
                .balance(5000.0)
                .currency("USD")
                .type(AccountType.CURRENT_ACCOUNT)
                .createdAt(new Date())
                .build();
       var savedAccount =  bankAccountRepository.save(account);

        mockMvc.perform(get("/bankAccount"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedAccount.getId()))
                .andExpect(jsonPath("$[0].currency").value("USD"))    // first element currency
                .andExpect(jsonPath("$[0].balance").value(5000.0));

    }

    @Test
    void shouldUpdateAccountViaPut() throws Exception {
        // 1️⃣ Save initial account
        BankAccount account = BankAccount.builder()
                .balance(5000.0)
                .currency("USD")
                .type(AccountType.CURRENT_ACCOUNT)
                .createdAt(new Date())
                .build();
        BankAccount savedAccount = bankAccountRepository.save(account);

        // 2️⃣ Prepare updated account (only balance changes)
        BankAccount updateRequest = BankAccount.builder()
                .balance(8000.0)
                .build();

        // 3️⃣ Perform PUT request
        mockMvc.perform(put("/bankAccount/{id}", savedAccount.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedAccount.getId()))
                .andExpect(jsonPath("$.balance").value(8000.0))
                .andExpect(jsonPath("$.currency").value("USD"))              // unchanged
                .andExpect(jsonPath("$.type").value("CURRENT_ACCOUNT"));     // unchanged

        // 4️⃣ Optionally: verify via GET
        mockMvc.perform(get("/bankAccount/{id}", savedAccount.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(8000.0))
                .andExpect(jsonPath("$.currency").value("USD"))
                .andExpect(jsonPath("$.type").value("CURRENT_ACCOUNT"));
    }


}
