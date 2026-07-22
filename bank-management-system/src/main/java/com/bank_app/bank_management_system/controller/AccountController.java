package com.bank_app.bank_management_system.controller;


import com.bank_app.bank_management_system.model.Accounts;
import com.bank_app.bank_management_system.model.Customer;
import com.bank_app.bank_management_system.repository.AccountsRepository;
import com.bank_app.bank_management_system.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository cutomerRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam String email) {

        Optional<Customer> customer = cutomerRepository.findByEmail(email);

        if (customer.isPresent()) {
            Accounts accounts = accountsRepository.findByCustomerId(customer.get().getId());
            if (accounts != null) {
                return accounts;
            }
        }
       return null;
    }
}
