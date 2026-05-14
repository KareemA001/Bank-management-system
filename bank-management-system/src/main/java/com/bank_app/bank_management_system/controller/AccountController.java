package com.bank_app.bank_management_system.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {


    @GetMapping(path = "/myaccount")
    public ResponseEntity<String> getAccountDetails() {
        return ResponseEntity.ok("This the account controller");
    }
}
