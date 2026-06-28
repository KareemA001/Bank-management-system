package com.bank_app.bank_management_system.config;


import com.bank_app.bank_management_system.exception.CustomAccessDeniedHandler;
import com.bank_app.bank_management_system.exception.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("!prod")
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityConfigurations(HttpSecurity http) throws Exception {
        http.sessionManagement(smc -> smc.invalidSessionUrl("/invalidSession")
                        .maximumSessions(3).maxSessionsPreventsLogin(true).expiredUrl("/expiresSession"))
                .redirectToHttps(https -> https.disable())
                .csrf(csrfConfig -> csrfConfig.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/myaccount",
                                "/myloans",
                                "/mycards",
                                "/mybalance")
                        .authenticated()
                        .requestMatchers("/mycontact",
                                "/mynotices",
                                "/register",
                                "/error",
                                "/invalidSession",
                                "/expiredSession")
                        .permitAll());
        http.formLogin(withDefaults());
        //http.httpBasic(HttpBasicConfigurer -> HttpBasicConfigurer.disable());
        http.httpBasic(hbc -> hbc
                .authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc -> ehc
                .accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker passwordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
