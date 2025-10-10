package com.nnk.springboot.service;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    /**
     * Retourne la liste de tous les RuleName.
     */
    public List<RuleName> getAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    /**
     * Retourne un RuleName par son id.
     */
    public Optional<RuleName> getRuleNameById(Integer id) {
        return ruleNameRepository.findById(id);
    }

    /**
     * Sauvegarde un nouveau RuleName ou met à jour un existant.
     */
    public RuleName saveRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Supprime un RuleName par son id.
     */
    public void deleteRuleName(Integer id) {
        ruleNameRepository.deleteById(id);
    }

    /**
     * Vérifie si un RuleName existe par son id.
     */
    public boolean existsById(Integer id) {
        return ruleNameRepository.existsById(id);
    }
}
