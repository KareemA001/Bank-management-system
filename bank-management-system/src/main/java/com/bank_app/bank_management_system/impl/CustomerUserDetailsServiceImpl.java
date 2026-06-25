package com.bank_app.bank_management_system.impl;

import com.bank_app.bank_management_system.model.Customer;
import com.bank_app.bank_management_system.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class CustomerUserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = this.customerRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("There is no user with this username "+username));
        List<GrantedAuthority> authority = List.of(new SimpleGrantedAuthority(customer.getRole()));
        return new User(customer.getEmail(),customer.getPassword(),authority);
    }
}
