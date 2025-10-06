package com.nnk.springboot.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/app/login", "/images/**","/css/**", "/js/**").permitAll() // accès libre au login + statiques
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/app/login")      // ⚡ URL de ton LoginController
                        .defaultSuccessUrl("/app/secure/article-details", true) // après login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/app/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}
