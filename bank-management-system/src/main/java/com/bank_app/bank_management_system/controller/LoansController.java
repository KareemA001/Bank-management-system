package com.bank_app.bank_management_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @GetMapping(path = "/myloans")
    public ResponseEntity<String> getLoansDetails() {
        return ResponseEntity.ok("This is the loan controller");
    }
}
