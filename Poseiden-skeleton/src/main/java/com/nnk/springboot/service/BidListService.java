package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    /**
     * Récupère la liste complète des BidList.
     */
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    /**
     * Recherche un BidList par son ID.
     */
    public Optional<BidList> findById(Integer id) {
        return bidListRepository.findById(id);
    }

    /**
     * Sauvegarde un nouveau BidList dans la base.
     */
    public BidList save(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    /**
     * Met à jour un BidList existant.
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
     * Supprime un BidList par ID.
     */
    public void deleteById(Integer id) {
        bidListRepository.deleteById(id);
    }
}
