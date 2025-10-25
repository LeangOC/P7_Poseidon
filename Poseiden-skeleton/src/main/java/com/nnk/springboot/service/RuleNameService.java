package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service responsable de la gestion des entités {@link RuleName}.
 * <p>
 * Fournit des opérations CRUD et des méthodes utilitaires
 * pour manipuler les règles métier stockées en base.
 * </p>
 */
@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    /**
     * Retourne la liste complète des {@link RuleName}.
     *
     * @return liste des {@link RuleName} en base
     */
    public List<RuleName> getAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    /**
     * Recherche un {@link RuleName} par son identifiant.
     *
     * @param id identifiant du {@link RuleName}
     * @return un {@link Optional} contenant l’entité trouvée si elle existe
     */
    public Optional<RuleName> getRuleNameById(Integer id) {
        return ruleNameRepository.findById(id);
    }

    /**
     * Sauvegarde un nouveau {@link RuleName} ou met à jour un existant.
     *
     * @param ruleName entité à sauvegarder
     * @return l’entité {@link RuleName} persistée
     */
    public RuleName saveRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Supprime un {@link RuleName} à partir de son identifiant.
     *
     * @param id identifiant du {@link RuleName} à supprimer
     */
    public void deleteRuleName(Integer id) {
        ruleNameRepository.deleteById(id);
    }

    /**
     * Vérifie l’existence d’un {@link RuleName} selon son identifiant.
     *
     * @param id identifiant à vérifier
     * @return {@code true} si le {@link RuleName} existe, {@code false} sinon
     */
    public boolean existsById(Integer id) {
        return ruleNameRepository.existsById(id);
    }
}
