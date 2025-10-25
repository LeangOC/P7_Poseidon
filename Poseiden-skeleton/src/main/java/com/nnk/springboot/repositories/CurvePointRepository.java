package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository Spring Data JPA pour la gestion des entités {@link CurvePoint}.
 * <p>
 * Permet d’interagir avec la table <strong>CurvePoint</strong> et de réaliser
 * des opérations standards de persistance via les méthodes héritées de {@link JpaRepository}.
 * </p>
 *
 * <p>Exemple d’utilisation :</p>
 * <pre>{@code
 * Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
 * }</pre>
 *
 * @see JpaRepository
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
