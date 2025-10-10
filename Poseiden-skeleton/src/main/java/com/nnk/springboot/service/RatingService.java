package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * Récupère la liste complète des Rating.
     */
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    /**
     * Sauvegarde un nouveau Rating ou met à jour un Rating existant.
     */
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Récupère un Rating par son identifiant.
     */
    public Optional<Rating> findById(Integer id) {
        return ratingRepository.findById(id);
    }

    /**
     * Supprime un Rating par son identifiant.
     */
    public void deleteById(Integer id) {
        ratingRepository.deleteById(id);
    }
}
