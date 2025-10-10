package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    /**
     * Récupère la liste complète des trades.
     */
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    /**
     * Récupère un trade par son ID.
     */
    public Optional<Trade> findById(Integer id) {
        return tradeRepository.findById(id);
    }

    /**
     * Crée un nouveau trade ou met à jour un trade existant.
     */
    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    /**
     * Supprime un trade selon son ID.
     */
    public void deleteById(Integer id) {
        tradeRepository.deleteById(id);
    }

    /**
     * Vérifie si un trade existe.
     */
    public boolean existsById(Integer id) {
        return tradeRepository.existsById(id);
    }
}
