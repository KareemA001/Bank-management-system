package com.bank_app.bank_management_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping(path = "/mycontact")
    public ResponseEntity<String> getContactDetails() {
        return ResponseEntity.ok("This is the contact controller");
    }
}
