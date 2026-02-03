package com.crm.crm_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // すべてのURLへのアクセスを許可する（開発用）
                .anyRequest().permitAll()
            )
            // CSRF対策を無効化（これがないとPOSTリクエストで403エラーになることがある）
            .csrf(csrf -> csrf.disable());
            
        return http.build();
    }
}