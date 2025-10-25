package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

/**
 * Contrôleur gérant les opérations CRUD pour l'entité {@link Rating}.
 */
@Controller
public class RatingController {

    @Autowired
    private RatingService ratingService;

    /**
     * Affiche la liste de tous les ratings.
     *
     * @param model modèle pour passer la liste à la vue
     * @return la vue "rating/list"
     */
    @GetMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute("ratings", ratingService.findAll());
        return "rating/list";
    }

    /**
     * Affiche le formulaire d’ajout d’un nouveau rating.
     *
     * @param rating objet vide pour le formulaire
     * @return la vue "rating/add"
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    /**
     * Valide et enregistre un nouveau rating.
     *
     * @param rating objet à enregistrer
     * @param result résultat de la validation
     * @param model  modèle de la vue
     * @return redirection vers la liste si succès, sinon retour au formulaire
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ratingService.save(rating);
            model.addAttribute("ratings", ratingService.findAll());
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    /**
     * Affiche le formulaire de mise à jour pour un rating existant.
     *
     * @param id    identifiant du rating à modifier
     * @param model modèle contenant le rating à modifier
     * @return la vue "rating/update"
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    /**
     * Met à jour un rating existant.
     *
     * @param id      identifiant du rating à mettre à jour
     * @param rating  objet contenant les nouvelles valeurs
     * @param result  résultat de la validation
     * @param model   modèle pour la vue
     * @return redirection vers la liste des ratings
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/rating/list";
        }
        rating.setId(id);
        ratingService.save(rating);
        model.addAttribute("ratings", ratingService.findAll());
        return "redirect:/rating/list";
    }

    /**
     * Supprime un rating par son identifiant.
     *
     * @param id    identifiant du rating à supprimer
     * @param model modèle pour la vue
     * @return redirection vers la liste
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.deleteById(id);
        model.addAttribute("ratings", ratingService.findAll());
        return "redirect:/rating/list";
    }
}
