package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.DBUser;
import com.nnk.springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 🧾 Liste des utilisateurs (réservée ADMIN)
    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

@GetMapping("/add")
public String showAddForm(Model model) {
    model.addAttribute("user", new DBUser());
    return "user/add";
}

    // 🔹 Validation et sauvegarde
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("user") DBUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/add";
        }
        userService.save(user);
        return "redirect:/user/list";
    }

    // 📝 Formulaire d’édition
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        DBUser user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "user/update";
    }

    // 🔹 Validation de la mise à jour
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid DBUser user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "user/update";
        }
        userService.update(user);
        return "redirect:/user/list";
    }

    // ❌ Suppression
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect:/user/list";
    }
}
