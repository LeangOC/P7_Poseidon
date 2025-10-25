package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository Spring Data JPA pour la gestion des entités {@link BidList}.
 * <p>
 * Cette interface fournit les opérations CRUD (Create, Read, Update, Delete)
 * sur la table <strong>BidList</strong> sans nécessiter d'implémentation explicite.
 * </p>
 *
 * <p>Exemple d’utilisation :</p>
 * <pre>{@code
 * List<BidList> bids = bidListRepository.findAll();
 * }</pre>
 *
 * @author
 * @version 1.0
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
