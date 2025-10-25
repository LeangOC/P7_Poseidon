package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service gérant les opérations CRUD sur les entités {@link Rating}.
 * <p>
 * Ce service fait le lien entre la couche de persistance et la logique métier
 * pour toutes les opérations relatives aux notations.
 * </p>
 */
@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * Récupère la liste complète des {@link Rating}.
     *
     * @return une liste de toutes les entités {@link Rating} en base
     */
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    /**
     * Sauvegarde un nouveau {@link Rating} ou met à jour un {@link Rating} existant.
     *
     * @param rating entité à sauvegarder
     * @return l’entité {@link Rating} persistée
     */
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Récupère un {@link Rating} à partir de son identifiant.
     *
     * @param id identifiant du {@link Rating}
     * @return un {@link Optional} contenant le {@link Rating} correspondant s’il existe
     */
    public Optional<Rating> findById(Integer id) {
        return ratingRepository.findById(id);
    }

    /**
     * Supprime un {@link Rating} à partir de son identifiant.
     *
     * @param id identifiant du {@link Rating} à supprimer
     */
    public void deleteById(Integer id) {
        ratingRepository.deleteById(id);
    }
}
