package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * Représente une transaction ("Trade") dans le système.
 * <p>
 * Cette entité correspond à la table <b>trade</b> et contient les informations
 * relatives à un échange financier : compte, type, quantités, prix, etc.
 * </p>
 */
@Entity
@Table(name = "trade")
public class Trade {

    /**
     * Identifiant unique du Trade (clé primaire auto-générée).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Integer tradeId;

    /**
     * Compte associé à la transaction.
     */
    @NotBlank(message = "Account is mandatory")
    private String account;

    /**
     * Type de transaction (ex : "Buy", "Sell"...).
     */
    @NotBlank(message = "Type is mandatory")
    private String type;

    @Column(name = "buyQuantity")
    private Double buyQuantity;

    @Column(name = "sellQuantity")
    private Double sellQuantity;

    @Column(name = "buyPrice")
    private Double buyPrice;

    @Column(name = "sellPrice")
    private Double sellPrice;

    @Column(name = "tradeDate")
    private Timestamp tradeDate;

    private String security;
    private String status;
    private String trader;
    private String benchmark;
    private String book;
    private String creationName;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    private String revisionName;

    @Column(name = "revisionDate")
    private Timestamp revisionDate;

    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;

    /**
     * Constructeur par défaut requis par JPA.
     */
    public Trade() {}

    /**
     * Constructeur de commodité pour créer rapidement un Trade.
     *
     * @param account nom du compte
     * @param type    type de transaction
     */
    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }

    // --- Getters & Setters ---

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public Double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(Double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }







}
