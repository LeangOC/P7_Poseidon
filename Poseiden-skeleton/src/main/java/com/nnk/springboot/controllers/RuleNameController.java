package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Contrôleur gérant les opérations CRUD pour l'entité {@link RuleName}.
 */
@Controller
public class RuleNameController {

    @Autowired
    private RuleNameService ruleNameService;

    /**
     * Affiche la liste de tous les RuleName.
     *
     * @param model modèle contenant la liste des règles
     * @return la vue "ruleName/list"
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        return "ruleName/list";
    }

    /**
     * Affiche le formulaire d’ajout d’une nouvelle règle.
     *
     * @param bid objet {@link RuleName} vide pour initialiser le formulaire
     * @return la vue "ruleName/add"
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    /**
     * Valide et enregistre une nouvelle règle.
     *
     * @param ruleName objet à valider et enregistrer
     * @param result   résultat de la validation
     * @param model    modèle de la vue
     * @param ra       attributs pour messages flash
     * @return redirection vers la liste si succès, sinon retour au formulaire
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleName ruleName, BindingResult result,
                           Model model, RedirectAttributes ra) {
        if (!result.hasErrors()) {
            ruleNameService.saveRuleName(ruleName);
            ra.addFlashAttribute("successSaveMessage", "Votre règle a bien été ajoutée.");
            model.addAttribute("ruleName", ruleNameService.getAllRuleNames());
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    /**
     * Affiche le formulaire de mise à jour d’une règle existante.
     *
     * @param id    identifiant de la règle
     * @param model modèle contenant la règle à modifier
     * @return la vue "ruleName/update"
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.getRuleNameById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    /**
     * Met à jour une règle existante.
     *
     * @param id       identifiant de la règle à mettre à jour
     * @param ruleName objet contenant les nouvelles valeurs
     * @param result   résultat de la validation
     * @param model    modèle de la vue
     * @return redirection vers la liste si succès, sinon retour au formulaire
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            ruleName.setId(id);
            return "ruleName/update";
        }
        ruleNameService.saveRuleName(ruleName);
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        return "redirect:/ruleName/list";
    }

    /**
     * Supprime une règle par son identifiant.
     *
     * @param id    identifiant de la règle à supprimer
     * @param model modèle pour la vue
     * @return redirection vers la liste des règles
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.getRuleNameById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
        ruleNameService.deleteRuleName(ruleName.getId());
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        return "redirect:/ruleName/list";
    }
}
