package com.nnk.springboot.domain;

import jakarta.persistence.*;

/**
 * Représente une règle métier ("RuleName") utilisée dans le système.
 * <p>
 * Cette entité correspond à la table <b>rulename</b> et stocke les informations
 * nécessaires à la définition et à l’exécution d’une règle (nom, description, SQL...).
 * </p>
 */
@Entity
@Table(name = "rulename")
public class RuleName {

    /**
     * Identifiant unique de la règle (clé primaire auto-générée).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String json;
    private String template;
    private String sqlStr;
    private String sqlPart;

    /**
     * Constructeur par défaut requis par JPA.
     */
    public RuleName() {}

    /**
     * Constructeur de commodité pour créer une règle complète.
     *
     * @param name        nom de la règle
     * @param description description de la règle
     * @param json        représentation JSON associée
     * @param template    modèle de la règle
     * @param sqlStr      requête SQL principale
     * @param sqlPart     partie SQL additionnelle
     */
    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    // --- Getters & Setters ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }
}
