package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur gérant les opérations CRUD pour l'entité {@link BidList}.
 * <p>
 * Cette classe permet d'afficher la liste des BidList, d'ajouter,
 * de modifier et de supprimer des enregistrements.
 * </p>
 */
@Controller
@RequestMapping("/bidList")
public class BidListController {

    @Autowired
    private BidListService bidListService;

    /**
     * Affiche la liste complète des BidList.
     *
     * @param model modèle utilisé pour passer les données à la vue
     * @return la vue "bidList/list"
     */
    @GetMapping("/list")
    public String home(Model model) {
        model.addAttribute("bidLists", bidListService.findAll());
        return "bidList/list";
    }

    /**
     * Affiche le formulaire d’ajout d’un nouveau BidList.
     *
     * @param model modèle pour initialiser le formulaire
     * @return la vue "bidList/add"
     */
    @GetMapping("/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidList());
        return "bidList/add";
    }

    /**
     * Valide et enregistre un nouveau BidList.
     *
     * @param bidList l'objet à valider et enregistrer
     * @param result  résultat de la validation
     * @param model   modèle pour la vue
     * @return redirection vers la liste si succès, sinon retour au formulaire
     */
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidList bidList,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.save(bidList);
        return "redirect:/bidList/list";
    }

    /**
     * Affiche le formulaire de mise à jour pour un BidList existant.
     *
     * @param id    identifiant du BidList à modifier
     * @param model modèle pour passer l'objet à la vue
     * @return la vue "bidList/update"
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid BidList Id:" + id));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    /**
     * Met à jour un BidList existant.
     *
     * @param id      identifiant du BidList à mettre à jour
     * @param bidList l'objet contenant les nouvelles valeurs
     * @param result  résultat de la validation
     * @param model   modèle pour la vue
     * @return redirection vers la liste si succès, sinon retour au formulaire
     */
    @PostMapping("/update/{id}")
    public String updateBid(@PathVariable("id") Integer id,
                            @Valid @ModelAttribute("bidList") BidList bidList,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            bidList.setBidListId(id);
            return "bidList/update";
        }
        bidListService.update(bidList);
        return "redirect:/bidList/list";
    }

    /**
     * Supprime un BidList par son identifiant.
     *
     * @param id identifiant du BidList à supprimer
     * @return redirection vers la liste des BidList
     */
    @GetMapping("/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        bidListService.deleteById(id);
        return "redirect:/bidList/list";
    }
}
