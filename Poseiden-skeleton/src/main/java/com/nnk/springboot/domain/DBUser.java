package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Entité représentant un utilisateur stocké en base de données.
 * <p>
 * Cette classe est mappée sur la table <b>users</b> et contient les informations
 * d'identification et de profil d'un utilisateur de l'application.
 * </p>
 * <p>
 * Les contraintes de validation garantissent que les données saisies respectent
 * les règles de sécurité et d'intégrité (ex. : mot de passe fort, champs obligatoires).
 * </p>
 */
@Entity
@Table(name = "users")
public class DBUser {

    /**
     * Identifiant unique de l'utilisateur.
     * <p>
     * Il est généré automatiquement par la base de données.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Nom d'utilisateur (login) utilisé pour l'authentification.
     * <p>
     * Ce champ est obligatoire.
     * </p>
     */
    @NotBlank(message = "Username is mandatory")
    private String username;

    /**
     * Mot de passe de l'utilisateur.
     * <p>
     * Ce champ est obligatoire et doit respecter plusieurs contraintes :
     * <ul>
     *     <li>Au moins 8 caractères</li>
     *     <li>Contenir au moins une majuscule</li>
     *     <li>Contenir au moins un chiffre</li>
     *     <li>Contenir au moins un caractère spécial</li>
     * </ul>
     * Ces règles visent à garantir un niveau de sécurité suffisant pour la connexion.
     * </p>
     */
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be 8 or more characters in length.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}:;\"'<>,.?/]).{8,}$",
            message = "Password must contain 1 or more uppercase characters, 1 or more digit characters, and 1 or more special characters."
    )
    private String password;

    /**
     * Nom complet de l'utilisateur.
     * <p>
     * Ce champ est obligatoire et peut être utilisé à des fins d’affichage
     * dans l’interface utilisateur.
     * </p>
     */
    @NotBlank(message = "FullName is mandatory")
    private String fullname;

    /**
     * Rôle attribué à l'utilisateur.
     * <p>
     * Ce champ définit les autorisations et les accès disponibles pour cet utilisateur.
     * Par exemple : <code>ADMIN</code>, <code>USER</code>.
     * </p>
     */
    @NotBlank(message = "Role is mandatory")
    private String role;

    // --- Getters & Setters ---

    /**
     * Retourne l'identifiant de l'utilisateur.
     *
     * @return l'identifiant unique
     */
    public Integer getId() { return id; }

    /**
     * Définit l'identifiant de l'utilisateur.
     *
     * @param id l'identifiant à attribuer
     */
    public void setId(Integer id) { this.id = id; }

    /**
     * Retourne le nom d'utilisateur.
     *
     * @return le nom d'utilisateur (login)
     */
    public String getUsername() { return username; }

    /**
     * Définit le nom d'utilisateur.
     *
     * @param username le nom d'utilisateur à enregistrer
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Retourne le mot de passe de l'utilisateur (encodé).
     *
     * @return le mot de passe encodé
     */
    public String getPassword() { return password; }

    /**
     * Définit le mot de passe de l'utilisateur.
     *
     * @param password le mot de passe brut ou encodé
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Retourne le nom complet de l'utilisateur.
     *
     * @return le nom complet
     */
    public String getFullname() { return fullname; }

    /**
     * Définit le nom complet de l'utilisateur.
     *
     * @param fullname le nom complet à enregistrer
     */
    public void setFullname(String fullname) { this.fullname = fullname; }

    /**
     * Retourne le rôle de l'utilisateur.
     *
     * @return le rôle attribué
     */
    public String getRole() { return role; }

    /**
     * Définit le rôle de l'utilisateur.
     *
     * @param role le rôle à attribuer
     */
    public void setRole(String role) { this.role = role; }
}
