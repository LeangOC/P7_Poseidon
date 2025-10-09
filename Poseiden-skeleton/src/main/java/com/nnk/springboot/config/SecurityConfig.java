package com.nnk.springboot.config;

import com.nnk.springboot.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationSuccessHandler customSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

    .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/app/login", "/css/**", "/images/**", "/js/**").permitAll()

                // HOME pages
                .requestMatchers("/bidList/list").hasRole("ADMIN")
                .requestMatchers("/trade/list").hasAnyRole("USER", "ADMIN")

                // Autres modules
                .requestMatchers("/curvePoint/**").hasRole("ADMIN")
                .requestMatchers("/rating/list").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/rating/**").hasRole("ADMIN") // ex: add, update, delete
                .requestMatchers("/ruleName/**").hasRole("ADMIN")

                // Gestion des utilisateurs
                .requestMatchers("/user/list").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("ADMIN")

                .anyRequest().authenticated()
        )
                .formLogin(form -> form
                        .loginPage("/app/login")
                        .loginProcessingUrl("/perform_login")
                        .successHandler(customSuccessHandler)
                        .failureUrl("/app/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                        .logoutSuccessUrl("/app/login?logout=true")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex.accessDeniedPage("/app/error"));


        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }
}
