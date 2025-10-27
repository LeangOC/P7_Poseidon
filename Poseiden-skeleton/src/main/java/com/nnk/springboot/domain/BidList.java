package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * Représente une offre ("BidList") dans le système.
 * <p>
 * Cette entité correspond à la table <b>bidlist</b> en base de données.
 * Elle contient les informations relatives à une offre financière, telles que
 * le compte, le type, la quantité d'achat/vente et d'autres métadonnées.
 * </p>
 *
 * @author
 */
@Entity
@Table(name = "bidlist")
public class BidList {

    /**
     * Identifiant unique de la BidList (clé primaire auto-générée).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_list_id")
    private Integer bidListId;

    /**
     * Nom du compte associé à la BidList.
     */
    @NotBlank(message = "Account is mandatory")
    private String account;

    /**
     * Type de la BidList (ex : "Spot", "Forward"...).
     */
    @NotBlank(message = "Type is mandatory")
    private String type;

    /**
     * Quantité d’achat.
     */
    @Column(name = "bidQuantity")
    private Double bidQuantity;

    /**
     * Quantité de vente.
     */
    @Column(name = "askQuantity")
    private Double askQuantity;

    private Double bid;
    private Double ask;
    private String benchmark;

    /**
     * Date de la BidList.
     */
    @Column(name = "bidListDate")
    private Timestamp bidListDate;

    private String commentary;
    private String security;
    private String status;
    private String trader;
    private String book;
    private String creationName;

    /**
     * Date de création de la BidList.
     */
    @Column(name = "creationDate")
    private Timestamp creationDate;

    private String revisionName;

    /**
     * Date de révision de la BidList.
     */
    @Column(name = "revisionDate")
    private Timestamp revisionDate;

    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;

    /**
     * Constructeur par défaut requis par JPA.
     */
    public BidList() {}

    /**
     * Constructeur de commodité pour créer rapidement une BidList.
     *
     * @param account     le nom du compte
     * @param type        le type d’opération
     * @param bidQuantity la quantité d’achat
     */
    public BidList(String account, String type, Double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    public Integer getBidListId() {
        return bidListId;
    }

    public void setBidListId(Integer bidListId) {
        this.bidListId = bidListId;
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

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public Double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(Double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getBidListDate() {
        return bidListDate;
    }

    public void setBidListDate(Timestamp bidListDate) {
        this.bidListDate = bidListDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
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

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }


    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

}
