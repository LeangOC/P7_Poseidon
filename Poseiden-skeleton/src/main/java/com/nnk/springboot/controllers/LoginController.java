package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Contrôleur gérant l’authentification et les redirections liées aux rôles utilisateur.
 * <p>
 * Il définit les pages de connexion, d’accueil et de gestion des erreurs d’accès.
 * </p>
 */
@Controller
public class LoginController {

    /**
     * Redirige automatiquement la racine ("/") vers la page de connexion.
     *
     * @return redirection vers "/app/login"
     */
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/app/login";
    }

    /**
     * Redirige l’administrateur connecté vers la liste des BidList.
     *
     * @param model modèle de la vue
     * @return redirection vers "/bidList/list"
     */
    @RequestMapping("/admin/home")
    public String adminHome(Model model) {
        return "redirect:/bidList/list";
    }

    /**
     * Affiche la page de connexion personnalisée.
     *
     * @return la vue "login"
     */
    @GetMapping("/app/login")
    public String login() {
        return "login";
    }

    /**
     * Affiche la page d’accueil pour un utilisateur non administrateur.
     *
     * @return la vue "home_user"
     */
    @GetMapping("/user/home_user")
    public String homeUser() {
        return "home_user";
    }

    /**
     * Affiche une page d’erreur 403 en cas d’accès non autorisé.
     *
     * @return un objet {@link ModelAndView} contenant le message d’erreur
     */
    @GetMapping("/app/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMsg", "Vous n'êtes pas autorisé à accéder à cette ressource.");
        mav.setViewName("403");
        return mav;
    }
}
