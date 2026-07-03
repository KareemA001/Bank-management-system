package com.bank_app.bank_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug=true)
public class BankManagementSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(BankManagementSystemApplication.class, args);
	}

}
