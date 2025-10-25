package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

/**
 * Contrôleur gérant les opérations CRUD pour l'entité {@link Trade}.
 */
@Controller
public class TradeController {

    @Autowired
    private TradeService tradeService;

    /**
     * Affiche la liste des trades.
     *
     * @param model modèle contenant la liste des trades
     * @return la vue "trade/list"
     */
    @GetMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("trades", tradeService.findAll());
        return "trade/list";
    }

    /**
     * Affiche le formulaire d’ajout d’un nouveau trade.
     *
     * @param model modèle pour initialiser le formulaire
     * @return la vue "trade/add"
     */
    @GetMapping("/trade/add")
    public String addTradeForm(Model model) {
        model.addAttribute("trade", new Trade());
        return "trade/add";
    }

    /**
     * Valide et enregistre un nouveau trade.
     *
     * @param trade  objet à enregistrer
     * @param result résultat de la validation
     * @param model  modèle pour la vue
     * @return redirection vers la liste si succès, sinon retour au formulaire
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeService.save(trade);
            model.addAttribute("trades", tradeService.findAll());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    /**
     * Affiche le formulaire de mise à jour pour un trade existant.
     *
     * @param id    identifiant du trade à modifier
     * @param model modèle contenant les données à modifier
     * @return la vue "trade/update"
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    /**
     * Met à jour un trade existant.
     *
     * @param id      identifiant du trade à mettre à jour
     * @param trade   objet contenant les nouvelles valeurs
     * @param result  résultat de la validation
     * @param model   modèle pour la vue
     * @return redirection vers la liste si succès, sinon retour au formulaire
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/update";
        }
        trade.setTradeId(id);
        tradeService.save(trade);
        model.addAttribute("trades", tradeService.findAll());
        return "redirect:/trade/list";
    }

    /**
     * Supprime un trade par son identifiant.
     *
     * @param id    identifiant du trade à supprimer
     * @param model modèle pour la vue
     * @return redirection vers la liste des trades
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.deleteById(id);
        model.addAttribute("trades", tradeService.findAll());
        return "redirect:/trade/list";
    }
}
