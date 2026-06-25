package com.bank_app.bank_management_system.config;

import com.bank_app.bank_management_system.impl.CustomerUserDetailsServiceImpl;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final CustomerUserDetailsServiceImpl customerUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomUsernamePasswordAuthenticationProvider(CustomerUserDetailsServiceImpl customerUserDetailsService,
                                                        PasswordEncoder passwordEncoder) {
        this.customerUserDetailsService = customerUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails returnedUser = customerUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(username, password, returnedUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
