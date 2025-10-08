package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    // Route racine → login
    @GetMapping("/")
    public String root() {
        return "login";
    }

    @GetMapping("/app/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/home_user")
    public String homeUser() {
        return "home_user";
    }

    @GetMapping("/admin/home_admin")
    public String homeAdmin() {
        return "home_admin";
    }

    @GetMapping("/app/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMsg", "Vous n'êtes pas autorisé à accéder à cette ressource.");
        mav.setViewName("403");
        return mav;
    }
}
