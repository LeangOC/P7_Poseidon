package com.nnk.springboot.domain;

import java.sql.Timestamp;
import jakarta.persistence.*;

/**
 * Représente un point de courbe ("CurvePoint") dans le système.
 * <p>
 * Cette entité correspond à la table <b>curvepoint</b> en base de données
 * et contient les données liées à une courbe financière (ID, date, valeur...).
 * </p>
 */
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    /**
     * Identifiant unique du CurvePoint (clé primaire auto-générée).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    /**
     * Identifiant de la courbe à laquelle ce point appartient.
     */
    @Column(name = "CurveId")
    private Integer curveId;

    /**
     * Date de référence du point de courbe.
     */
    @Column(name = "asOfDate")
    private Timestamp asOfDate;

    /**
     * Terme associé au point (maturité).
     */
    private Double term;

    /**
     * Valeur du point sur la courbe.
     */
    private Double value;

    /**
     * Date de création du point.
     */
    @Column(name = "creationDate")
    private Timestamp creationDate;

    /**
     * Constructeur par défaut requis par JPA.
     */
    public CurvePoint() {}

    /**
     * Constructeur de commodité pour créer un CurvePoint.
     *
     * @param curveId identifiant de la courbe
     * @param term    terme (maturité)
     * @param value   valeur du point
     */
    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    // --- Getters & Setters ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
