package com.bank_app.bank_management_system.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.BadRequestException;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class RequestValidationBeforeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String header = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            header = header.trim();
            if (StringUtils.startsWithIgnoreCase(header,"Basic ")) {
                byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decoded;
                try {
                    decoded= Base64.getDecoder().decode(base64Token);
                    String token = new String(decoded,StandardCharsets.UTF_8);
                    int delim = token.indexOf(":");
                    if (delim == -1) {
                        throw new BadCredentialsException("Invalid token");
                    }
                    String email = token.substring(0,delim);
                    if(email.toLowerCase().contains("test")) {
                        httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }
                }catch (IllegalArgumentException exception) {
                    throw new BadRequestException("Invalid token");
                }
            }
        }
        filterChain.doFilter(httpRequest,httpResponse);
    }
}
