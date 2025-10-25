package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository Spring Data JPA pour la gestion des entités {@link Trade}.
 * <p>
 * Permet la manipulation des enregistrements de la table <strong>Trade</strong>,
 * notamment la création, la lecture, la mise à jour et la suppression.
 * </p>
 *
 * <p>Exemple d’utilisation :</p>
 * <pre>{@code
 * tradeRepository.deleteById(id);
 * }</pre>
 *
 * @see JpaRepository
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
