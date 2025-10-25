package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository Spring Data JPA pour la gestion des entités {@link Rating}.
 * <p>
 * Cette interface offre un accès simplifié à la base de données pour la table <strong>Rating</strong>,
 * en exploitant les méthodes génériques de {@link JpaRepository}.
 * </p>
 *
 * <p>Exemple d’utilisation :</p>
 * <pre>{@code
 * List<Rating> ratings = ratingRepository.findAll();
 * }</pre>
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
