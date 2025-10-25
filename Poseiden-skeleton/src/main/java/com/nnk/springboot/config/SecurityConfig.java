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

/**
 * Configuration principale de la sécurité Spring Security pour l'application.
 * <p>
 * Cette classe définit les règles d'accès aux différentes routes, la configuration du
 * formulaire de connexion, ainsi que les composants nécessaires à l'authentification
 * (gestionnaire de succès, encodeur de mots de passe, etc.).
 * </p>
 *
 * <h2>Fonctionnalités principales :</h2>
 * <ul>
 *     <li>Configuration des URL publiques et protégées par rôle</li>
 *     <li>Gestion de l’authentification par formulaire</li>
 *     <li>Définition du comportement en cas de succès ou d’échec de connexion</li>
 *     <li>Configuration du chiffrement des mots de passe avec BCrypt</li>
 * </ul>
 *
 * @author ...
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /** Service personnalisé de gestion des utilisateurs et de leurs rôles. */
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /** Gestionnaire de succès d'authentification personnalisé. */
    @Autowired
    private AuthenticationSuccessHandler customSuccessHandler;

    /**
     * Définit la chaîne de filtres de sécurité Spring Security.
     * <p>
     * Configure les routes accessibles selon le rôle, la page de connexion,
     * la gestion des erreurs d’accès et la déconnexion.
     * </p>
     *
     * @param http objet {@link HttpSecurity} utilisé pour configurer les règles de sécurité
     * @return la chaîne de filtres {@link SecurityFilterChain} construite
     * @throws Exception si une erreur de configuration survient
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Pages publiques (accès libre)
                        .requestMatchers("/", "/app/login", "/css/**", "/images/**", "/js/**").permitAll()

                        // Accès par rôle
                        .requestMatchers("/bidList/list").hasRole("ADMIN")
                        .requestMatchers("/trade/list").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/curvePoint/**").hasRole("ADMIN")
                        .requestMatchers("/rating/list").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/rating/**").hasRole("ADMIN")
                        .requestMatchers("/ruleName/**").hasRole("ADMIN")
                        .requestMatchers("/user/list").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("ADMIN")

                        // Toute autre requête nécessite une authentification
                        .anyRequest().authenticated()
                )
                // Configuration du formulaire de connexion
                .formLogin(form -> form
                        .loginPage("/app/login")
                        .loginProcessingUrl("/perform_login")
                        .successHandler(customSuccessHandler)
                        .failureUrl("/app/login?error=true")
                        .permitAll()
                )
                // Configuration de la déconnexion
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                        .logoutSuccessUrl("/app/login?logout=true")
                        .permitAll()
                )
                // Page d’erreur personnalisée pour les accès refusés
                .exceptionHandling(ex -> ex.accessDeniedPage("/app/error"));

        return http.build();
    }

    /**
     * Déclare un bean pour l'encodeur de mots de passe utilisant l'algorithme BCrypt.
     *
     * @return un encodeur {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure le gestionnaire d’authentification de Spring Security.
     * <p>
     * Ce gestionnaire utilise le {@link CustomUserDetailsService} pour récupérer les utilisateurs
     * et le {@link BCryptPasswordEncoder} pour vérifier les mots de passe.
     * </p>
     *
     * @param http                   objet de configuration de la sécurité HTTP
     * @param bCryptPasswordEncoder  encodeur de mots de passe
     * @return une instance d'{@link AuthenticationManager}
     * @throws Exception si une erreur de configuration survient
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }
}
