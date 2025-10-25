package com.nnk.springboot.service;

import com.nnk.springboot.domain.DBUser;
import com.nnk.springboot.repositories.DBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service de gestion de l’authentification utilisateur.
 * <p>
 * Implémente l’interface {@link UserDetailsService} pour fournir
 * les détails d’un utilisateur à Spring Security.
 * </p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DBUserRepository dbUserRepository;

    /**
     * Charge un utilisateur à partir de son nom d’utilisateur.
     *
     * @param username nom d’utilisateur
     * @return un objet {@link UserDetails} contenant les informations de sécurité
     * @throws UsernameNotFoundException si l’utilisateur n’existe pas
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DBUser user = dbUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));
    }

    /**
     * Génère la liste des rôles attribués à l’utilisateur.
     *
     * @param role rôle de l’utilisateur
     * @return liste des autorités accordées
     */
    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}
