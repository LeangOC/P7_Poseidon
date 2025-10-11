package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de dépôt (repository) pour la gestion des entités {@link DBUser}.
 * <p>
 * Cette interface étend {@link JpaRepository}, ce qui lui fournit automatiquement
 * toutes les opérations CRUD (Create, Read, Update, Delete) sur les utilisateurs,
 * sans nécessiter d’implémentation manuelle.
 * </p>
 * <p>
 * Des méthodes personnalisées peuvent être ajoutées pour effectuer des requêtes
 * spécifiques sur la table <b>users</b>.
 * </p>
 *
 * @see DBUser
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface DBUserRepository extends JpaRepository<DBUser, Integer> {

    /**
     * Recherche un utilisateur à partir de son nom d'utilisateur (login).
     * <p>
     * Cette méthode est automatiquement implémentée par Spring Data JPA,
     * en se basant sur la convention de nommage de la méthode (<code>findBy...()</code>).
     * </p>
     *
     * @param username le nom d'utilisateur à rechercher
     * @return l'entité {@link DBUser} correspondant au nom d'utilisateur fourni,
     *         ou {@code null} si aucun utilisateur ne correspond
     */
    DBUser findByUsername(String username);
}

