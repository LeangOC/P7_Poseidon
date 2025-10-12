package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CurvePointService {

    private final CurvePointRepository curvePointRepository;

    @Autowired
    public CurvePointService(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    /**
     * Récupère tous les CurvePoints en base.
     */
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    /**
     * Récupère un CurvePoint par son ID.
     */
    public Optional<CurvePoint> findById(Integer id) {
        return curvePointRepository.findById(id);
    }

    /**
     * Crée un nouveau CurvePoint (avec la date de création actuelle).
     */
    public CurvePoint save(CurvePoint curvePoint) {
        curvePoint.setCreationDate(Timestamp.from(Instant.now()));
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Met à jour un CurvePoint existant.
     */
    public CurvePoint update(Integer id, CurvePoint updatedCurvePoint) {
        return curvePointRepository.findById(id)
                .map(existing -> {
                    existing.setCurveId(updatedCurvePoint.getCurveId());
                    existing.setAsOfDate(updatedCurvePoint.getAsOfDate());
                    existing.setTerm(updatedCurvePoint.getTerm());
                    existing.setValue(updatedCurvePoint.getValue());
                    existing.setCreationDate(Timestamp.from(Instant.now()));
                    return curvePointRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("CurvePoint with ID " + id + " not found"));
    }

    /**
     * Supprime un CurvePoint par ID.
     */
    public void deleteById(Integer id) {
        if (curvePointRepository.existsById(id)) {
            curvePointRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("CurvePoint with ID " + id + " not found");
        }
    }
}
