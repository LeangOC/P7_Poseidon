package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service gérant les opérations CRUD sur les entités {@link Trade}.
 * <p>
 * Ce service fournit des méthodes pour créer, lire, mettre à jour
 * et supprimer des transactions de trading.
 * </p>
 */
@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     * Récupère la liste complète des {@link Trade}.
     *
     * @return liste des transactions enregistrées
     */
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    /**
     * Recherche un {@link Trade} par son identifiant.
     *
     * @param id identifiant du {@link Trade}
     * @return un {@link Optional} contenant le {@link Trade} s’il existe
     */
    public Optional<Trade> findById(Integer id) {
        return tradeRepository.findById(id);
    }

    /**
     * Crée ou met à jour un {@link Trade}.
     *
     * @param trade entité à sauvegarder
     * @return le {@link Trade} persisté
     */
    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    /**
     * Supprime un {@link Trade} selon son identifiant.
     *
     * @param id identifiant du {@link Trade} à supprimer
     */
    public void deleteById(Integer id) {
        tradeRepository.deleteById(id);
    }

    /**
     * Vérifie si un {@link Trade} existe selon son identifiant.
     *
     * @param id identifiant à vérifier
     * @return {@code true} si le {@link Trade} existe, {@code false} sinon
     */
    public boolean existsById(Integer id) {
        return tradeRepository.existsById(id);
    }
}
