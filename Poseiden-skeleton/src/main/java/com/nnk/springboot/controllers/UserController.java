package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.DBUser;
import com.nnk.springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur Spring MVC gérant les opérations liées aux utilisateurs.
 * <p>
 * Cette classe est responsable de l’affichage des pages utilisateur, de la
 * validation des formulaires et de la communication avec la couche service
 * {@link UserService}.
 * </p>
 * <p>
 * Toutes les routes de ce contrôleur sont préfixées par <b>/user</b>.
 * </p>
 *
 * @see DBUser
 * @see UserService
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /** Service gérant la logique métier liée aux utilisateurs. */
    @Autowired
    private UserService userService;

    /**
     * Affiche la liste de tous les utilisateurs enregistrés.
     * <p>
     * Accessible uniquement aux administrateurs (via la configuration Spring Security).
     * </p>
     *
     * @param model le modèle utilisé pour transmettre la liste des utilisateurs à la vue
     * @return le nom de la vue Thymeleaf affichant la liste des utilisateurs
     */
    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    /**
     * Affiche le formulaire d’ajout d’un nouvel utilisateur.
     *
     * @param model le modèle contenant un nouvel objet {@link DBUser} vide pour le formulaire
     * @return le nom de la vue Thymeleaf pour l’ajout d’un utilisateur
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new DBUser());
        return "user/add";
    }

    /**
     * Valide et enregistre un nouvel utilisateur soumis via le formulaire.
     * <p>
     * Si des erreurs de validation sont détectées (annotations Jakarta Validation),
     * le formulaire d’ajout est réaffiché avec les messages d’erreur correspondants.
     * </p>
     *
     * @param user   l’utilisateur à enregistrer
     * @param result le résultat de la validation du formulaire
     * @param model  le modèle de la vue
     * @return redirection vers la liste des utilisateurs si succès, sinon la vue d’ajout
     */
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("user") DBUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/add";
        }
        userService.save(user);
        return "redirect:/user/list";
    }

    /**
     * Affiche le formulaire d’édition d’un utilisateur existant.
     *
     * @param id    l’identifiant de l’utilisateur à modifier
     * @param model le modèle contenant les informations de l’utilisateur à afficher
     * @return le nom de la vue Thymeleaf pour la mise à jour de l’utilisateur
     * @throws IllegalArgumentException si l’utilisateur avec l’ID spécifié n’existe pas
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        DBUser user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Valide et met à jour un utilisateur existant.
     * <p>
     * Si des erreurs de validation sont détectées, la page d’édition est réaffichée.
     * </p>
     *
     * @param id     l’identifiant de l’utilisateur à mettre à jour
     * @param user   l’utilisateur contenant les nouvelles informations
     * @param result le résultat de la validation du formulaire
     * @param model  le modèle de la vue
     * @return redirection vers la liste des utilisateurs si succès, sinon la vue de mise à jour
     */
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid DBUser user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            model.addAttribute("user", user); // ✅ indispensable pour éviter TemplateProcessingException
            return "user/update";
        }
        userService.update(user);
        return "redirect:/user/list";
    }

    /**
     * Supprime un utilisateur en fonction de son identifiant.
     *
     * @param id l’identifiant de l’utilisateur à supprimer
     * @return redirection vers la liste des utilisateurs après suppression
     */
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect:/user/list";
    }
}
