package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;

/**
 * Contrôleur gérant les opérations CRUD pour l'entité {@link CurvePoint}.
 * <p>
 * Cette classe permet d'afficher, d'ajouter, de modifier et de supprimer des points de courbe.
 * </p>
 */
@Controller
public class CurveController {

    @Autowired
    private com.nnk.springboot.service.CurvePointService curvePointService;

    /**
     * Affiche la liste des {@link CurvePoint}.
     *
     * @param model modèle utilisé pour passer la liste à la vue
     * @return la vue "curvePoint/list"
     */
    @GetMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "curvePoint/list";
    }

    /**
     * Affiche le formulaire d’ajout d’un nouveau {@link CurvePoint}.
     *
     * @param bid un objet {@link CurvePoint} vide pour initialiser le formulaire
     * @return la vue "curvePoint/add"
     */
    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    /**
     * Valide et enregistre un nouveau {@link CurvePoint}.
     *
     * @param curvePoint l’objet à valider et enregistrer
     * @param result     résultat de la validation
     * @param model      modèle pour la vue
     * @return redirection vers la liste si succès, sinon retour au formulaire
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/add";
        }
        curvePointService.save(curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**
     * Affiche le formulaire de mise à jour pour un {@link CurvePoint} existant.
     *
     * @param id    identifiant du point de courbe à modifier
     * @param model modèle contenant les données du point à modifier
     * @return la vue "curvePoint/update"
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    /**
     * Met à jour un {@link CurvePoint} existant.
     *
     * @param id          identifiant du point à mettre à jour
     * @param curvePoint  objet contenant les nouvelles valeurs
     * @param result      résultat de la validation
     * @param model       modèle pour la vue
     * @return redirection vers la liste si succès, sinon retour au formulaire
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePointService.update(id, curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**
     * Supprime un {@link CurvePoint} par son identifiant.
     *
     * @param id identifiant du point à supprimer
     * @param model modèle pour la vue
     * @return redirection vers la liste des points de courbe
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curvePointService.deleteById(id);
        return "redirect:/curvePoint/list";
    }
}
