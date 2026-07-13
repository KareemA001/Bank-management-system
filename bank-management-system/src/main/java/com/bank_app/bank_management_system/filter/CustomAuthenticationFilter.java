package com.bank_app.bank_management_system.filter;

import com.bank_app.bank_management_system.model.LoginRequestDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CustomAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {



    }

    public String returnUsername(@RequestBody LoginRequestDTO loginRequest) {
        return loginRequest.username();
    }

    public String returnPassword(@RequestBody LoginRequestDTO loginRequestDTO) {
        return loginRequestDTO.password();
    }
}
