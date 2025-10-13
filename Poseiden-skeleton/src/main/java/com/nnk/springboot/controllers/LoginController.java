package com.nnk.springboot.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    // Redirection automatique de "/" vers "/app/login"
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/app/login";
    }


    @RequestMapping("/admin/home")
    public String adminHome(Model model)
    {
        return "redirect:/bidList/list";
    }


    // Page de login personnalisée
    @GetMapping("/app/login")
    public String login() {
        return "login";
    }

    // Page d'accueil utilisateur
    @GetMapping("/user/home_user")
    public String homeUser() {
        return "home_user";
    }

    // Page d'erreur (403)
    @GetMapping("/app/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMsg", "Vous n'êtes pas autorisé à accéder à cette ressource.");
        mav.setViewName("403");
        return mav;
    }
}
