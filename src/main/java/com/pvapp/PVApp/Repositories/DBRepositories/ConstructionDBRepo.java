package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Construction;

import com.pvapp.PVApp.Repositories.CRUD;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;


@Repository
@Slf4j
public class ConstructionDBRepo implements CRUD<Construction> {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void update(Construction construction) {
        log.info("Merging construction --repository");
        em.merge(construction);
    }

    @Override
    @Transactional
    public void create(Construction construction) {
        log.info("Persisting construction --repository");
        em.persist(construction);
    }

    @Override
    @Transactional
    public void delete(int id) {
        log.info("Deleting construction by Id --repository: " + id);
        em.remove(em.find(Construction.class, id));
    }


    @Override
    public Collection<Construction> printAll() {
        log.info("Getting all constructions ordered by manufacturerm, roogtype, roofmaterial--repository");
        return em.createQuery("from Construction order by manufacturer, rooftype, roofmaterial", Construction.class).getResultList();
    }

    @Override
    public Construction printbyid(int id) {
        log.info("Getting construction by id --repository: " + id);
        return em.find(Construction.class, id);
    }

    public Collection<Construction> printAllIdOrder() {
        log.info("Getting all constructions ordered by id --repository");
        return em.createQuery("from Construction order by id", Construction.class).getResultList();
    }

    public Construction getByRoofTypeMaterial(String rooftype, String roofmaterial) {
        log.info("Getting construction by roof type and roof material --repository");
        try {
            return em.createQuery("from Construction C WHERE C.rooftype=:type AND C.roofmaterial=:material", Construction.class)
                    .setParameter("type", Construction.roofType.valueOf(rooftype))
                    .setParameter("material", Construction.roofMaterial.valueOf(roofmaterial))
                    .setMaxResults(1).getSingleResult();
        } catch (NoResultException ex) {
            if (rooftype.equals("DACH_PLASKI")) {
                log.info("Change roof material  from " + roofmaterial + " to: PAPA (this construction not exists in DB) --Repository (catch)");
                roofmaterial = "PAPA";
            } else if (rooftype.equals("DACH_SKOSNY")) {
                log.info("Change roof material  from " + roofmaterial + " to: BLACHOTRAPEZ (this construction not exists in DB) --Repository (catch)");
                roofmaterial = "BLACHOTRAPEZ";
            } else {
                log.info("Change roof material  from " + roofmaterial + " to: GRUNT (this construction not exists in DB) --Repository (catch)");
                roofmaterial = "GRUNT";
            }
        }
        return em.createQuery("from Construction C WHERE C.rooftype=:type AND C.roofmaterial=:material", Construction.class)
                .setParameter("type", Construction.roofType.valueOf(rooftype))
                .setParameter("material", Construction.roofMaterial.valueOf(roofmaterial))
                .setMaxResults(1).getSingleResult();
    }

    public Collection<Construction> getByManufactuerA() {
        log.info("Getting construction for manufaturer: Producent A");
        return em.createQuery("from Construction C WHERE C.manufacturer=:man", Construction.class).setParameter("man", "Producent A").getResultList();
    }

    @Transactional
    public void saveFromFilem(List<Construction> constructions) {
        log.info("Save constructions from file");
        for (Construction construction : constructions) {
            em.persist(construction);
        }
    }
}
