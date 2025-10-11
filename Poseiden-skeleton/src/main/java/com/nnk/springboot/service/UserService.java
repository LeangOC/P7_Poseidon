package com.nnk.springboot.service;

import com.nnk.springboot.domain.DBUser;
import com.nnk.springboot.repositories.DBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service gérant les opérations liées aux utilisateurs de l'application.
 * <p>
 * Cette classe interagit avec la couche {@link DBUserRepository} pour effectuer
 * les opérations CRUD (Create, Read, Update, Delete) sur les entités {@link DBUser}.
 * Elle assure également l'encodage des mots de passe avant la sauvegarde en base de données.
 * </p>
 */
@Service
public class UserService {

    /** Référentiel pour accéder aux données des utilisateurs. */
    @Autowired
    private DBUserRepository userRepository;

    /** Encodeur de mots de passe utilisé pour sécuriser les informations d'identification. */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Récupère la liste complète des utilisateurs.
     *
     * @return une liste de tous les {@link DBUser} enregistrés dans la base de données
     */
    public List<DBUser> findAll() {
        return userRepository.findAll();
    }

    /**
     * Recherche un utilisateur par son identifiant unique.
     *
     * @param id l'identifiant de l'utilisateur à rechercher
     * @return un {@link Optional} contenant l'utilisateur s'il existe, vide sinon
     */
    public Optional<DBUser> findById(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * Enregistre un nouvel utilisateur en base de données.
     * <p>
     * Le mot de passe de l'utilisateur est encodé avant la sauvegarde afin de garantir la sécurité.
     * </p>
     *
     * @param user l'utilisateur à enregistrer
     * @return l'utilisateur enregistré avec son identifiant généré
     */
    public DBUser save(DBUser user) {
        // On encode le mot de passe avant sauvegarde
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     * <p>
     * Si un nouveau mot de passe est fourni, il est encodé avant d'être enregistré.
     * Si aucun mot de passe n'est renseigné, l'ancien est conservé.
     * </p>
     *
     * @param updatedUser l'utilisateur contenant les nouvelles informations
     * @return l'utilisateur mis à jour
     * @throws IllegalArgumentException si l'utilisateur à mettre à jour n'existe pas
     */
    public DBUser update(DBUser updatedUser) {
        DBUser existingUser = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + updatedUser.getId()));

        existingUser.setFullname(updatedUser.getFullname());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setRole(updatedUser.getRole());

        // Si l’admin a changé le mot de passe, on le réencode
        if (!updatedUser.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        return userRepository.save(existingUser);
    }

    /**
     * Supprime un utilisateur de la base de données selon son identifiant.
     *
     * @param id l'identifiant de l'utilisateur à supprimer
     */
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
