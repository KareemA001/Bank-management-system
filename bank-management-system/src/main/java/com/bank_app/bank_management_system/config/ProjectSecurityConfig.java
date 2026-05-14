package com.bank_app.bank_management_system.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityConfigurations(HttpSecurity http) throws Exception {
        http.csrf(csrfConfig -> csrfConfig.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/myaccount",
                                "/myloans",
                                "/mycards",
                                "/mybalance")
                        .authenticated()
                        .requestMatchers("/mycontact",
                                "/mynotices",
                                "/register",
                                "/error")
                        .permitAll());
        //http.formLogin(withDefaults());
        //http.httpBasic(HttpBasicConfigurer -> HttpBasicConfigurer.disable());
        http.httpBasic(withDefaults());
        return http.build();
    }

    /*
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password("{bcrypt}$2a$12$M5KMSbtbvIl.HcYvXfCNBuK3ypz7S8EKM5LvWA23e/v4QIRP0Y1uu").authorities("read").build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}54321").authorities("admin").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    */

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker passwordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
