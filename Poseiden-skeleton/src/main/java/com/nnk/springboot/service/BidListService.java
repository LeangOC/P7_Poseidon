package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service gérant les opérations CRUD sur les entités {@link BidList}.
 * <p>
 * Cette classe interagit avec le {@link BidListRepository} pour accéder
 * aux données persistées dans la base.
 * </p>
 */
@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    /**
     * Récupère la liste complète des {@link BidList}.
     *
     * @return liste de toutes les entités {@link BidList}
     */
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    /**
     * Recherche un {@link BidList} par son identifiant.
     *
     * @param id identifiant du {@link BidList}
     * @return un {@link Optional} contenant le {@link BidList} s’il existe
     */
    public Optional<BidList> findById(Integer id) {
        return bidListRepository.findById(id);
    }

    /**
     * Sauvegarde un nouveau {@link BidList} dans la base de données.
     *
     * @param bidList entité à sauvegarder
     * @return l’entité sauvegardée
     */
    public BidList save(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    /**
     * Met à jour un {@link BidList} existant à partir des nouvelles valeurs.
     *
     * @param updatedBid entité contenant les nouvelles valeurs
     * @return le {@link BidList} mis à jour
     * @throws IllegalArgumentException si l’entité à mettre à jour n’existe pas
     */
    public BidList update(BidList updatedBid) {
        BidList existingBid = bidListRepository.findById(updatedBid.getBidListId())
                .orElseThrow(() -> new IllegalArgumentException("BidList not found with id: " + updatedBid.getBidListId()));

        existingBid.setAccount(updatedBid.getAccount());
        existingBid.setType(updatedBid.getType());
        existingBid.setBidQuantity(updatedBid.getBidQuantity());
        existingBid.setAskQuantity(updatedBid.getAskQuantity());
        existingBid.setBid(updatedBid.getBid());
        existingBid.setAsk(updatedBid.getAsk());
        existingBid.setBenchmark(updatedBid.getBenchmark());
        existingBid.setCommentary(updatedBid.getCommentary());
        existingBid.setSecurity(updatedBid.getSecurity());
        existingBid.setStatus(updatedBid.getStatus());
        existingBid.setTrader(updatedBid.getTrader());
        existingBid.setBook(updatedBid.getBook());
        existingBid.setDealName(updatedBid.getDealName());
        existingBid.setDealType(updatedBid.getDealType());
        existingBid.setSourceListId(updatedBid.getSourceListId());
        existingBid.setSide(updatedBid.getSide());

        return bidListRepository.save(existingBid);
    }

    /**
     * Supprime un {@link BidList} par son identifiant.
     *
     * @param id identifiant de l’entité à supprimer
     */
    public void deleteById(Integer id) {
        bidListRepository.deleteById(id);
    }
}
