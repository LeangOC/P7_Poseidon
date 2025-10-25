package com.nnk.springboot.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

/**
 * Gestionnaire personnalisé de succès d'authentification.
 * <p>
 * Cette classe détermine la page de redirection à utiliser après une authentification réussie,
 * en fonction du rôle de l'utilisateur connecté.
 * </p>
 *
 * <ul>
 *     <li><b>ROLE_ADMIN</b> → redirigé vers <code>/user/list</code></li>
 *     <li><b>ROLE_USER</b> → redirigé vers <code>/trade/list</code></li>
 *     <li>Autres rôles → redirigé vers <code>/app/error</code></li>
 * </ul>
 *
 * @author ...
 * @version 1.0
 */
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Méthode appelée automatiquement après une authentification réussie.
     * <p>
     * Elle redirige l'utilisateur vers une page spécifique selon son rôle.
     * </p>
     *
     * @param request        la requête HTTP initiale
     * @param response       la réponse HTTP à envoyer
     * @param authentication l'objet d'authentification contenant les informations de l'utilisateur
     * @throws IOException      en cas d'erreur d'entrée/sortie lors de la redirection
     * @throws ServletException en cas d'erreur liée au traitement de la requête
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/user/list");  // Redirection pour un administrateur
        } else if (roles.contains("ROLE_USER")) {
            response.sendRedirect("/trade/list"); // Redirection pour un utilisateur standard
        } else {
            response.sendRedirect("/app/error");  // Redirection par défaut en cas de rôle inconnu
        }
    }
}
