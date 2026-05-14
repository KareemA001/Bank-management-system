package com.bank_app.bank_management_system.controller;

import com.bank_app.bank_management_system.model.Customer;
import com.bank_app.bank_management_system.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashedPassword);
        Customer savedCustomer = customerRepository.save(customer);
        try {
            if (savedCustomer.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("User is successfully registered");
            }else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Register is failed");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Register is failed due to "+ex.getMessage());
        }
    }
}
