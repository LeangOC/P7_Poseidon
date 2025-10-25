package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Service gérant les opérations CRUD sur les entités {@link CurvePoint}.
 */
@Service
public class CurvePointService {

    private final CurvePointRepository curvePointRepository;

    @Autowired
    public CurvePointService(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    /**
     * Récupère tous les {@link CurvePoint} enregistrés.
     *
     * @return liste des {@link CurvePoint}
     */
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    /**
     * Récupère un {@link CurvePoint} par son identifiant.
     *
     * @param id identifiant du {@link CurvePoint}
     * @return un {@link Optional} contenant le {@link CurvePoint} trouvé
     */
    public Optional<CurvePoint> findById(Integer id) {
        return curvePointRepository.findById(id);
    }

    /**
     * Crée et sauvegarde un nouveau {@link CurvePoint}.
     * <p>La date de création est automatiquement renseignée avec la date actuelle.</p>
     *
     * @param curvePoint entité à sauvegarder
     * @return le {@link CurvePoint} sauvegardé
     */
    public CurvePoint save(CurvePoint curvePoint) {
        curvePoint.setCreationDate(Timestamp.from(Instant.now()));
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Met à jour un {@link CurvePoint} existant.
     *
     * @param id identifiant du {@link CurvePoint} à mettre à jour
     * @param updatedCurvePoint entité contenant les nouvelles valeurs
     * @return le {@link CurvePoint} mis à jour
     * @throws IllegalArgumentException si aucun {@link CurvePoint} n’est trouvé
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
     * Supprime un {@link CurvePoint} par son identifiant.
     *
     * @param id identifiant du {@link CurvePoint}
     * @throws IllegalArgumentException si aucun {@link CurvePoint} n’est trouvé
     */
    public void deleteById(Integer id) {
        if (curvePointRepository.existsById(id)) {
            curvePointRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("CurvePoint with ID " + id + " not found");
        }
    }
}
