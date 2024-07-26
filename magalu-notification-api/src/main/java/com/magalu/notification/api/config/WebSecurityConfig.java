package com.magalu.notification.api.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest()
                        .permitAll()
                        )
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                        .contentTypeOptions(withDefaults())
                        .frameOptions(withDefaults())
                        .disable())
                .build();
    }
}
