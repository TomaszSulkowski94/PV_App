package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Construction;
import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Entities.Inverter;
import com.pvapp.PVApp.Entities.PVModule;
import com.pvapp.PVApp.Repositories.CRUD;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;

@Repository
@Slf4j
public class InstalationDBRepo implements CRUD<Instalation> {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void update(Instalation instalation) {
        log.info("Merging instalation --repository");
        em.merge(instalation);
    }


    @Override
    @Transactional
    public void create(Instalation instalation) {
        log.info("Persisting instalation --repository");
        em.persist(instalation);
    }

    @Override
    @Transactional
    public void delete(int id) {
        log.info("Deleting instalation by id --repository: " + id);
        em.remove(em.find(Instalation.class, id));
    }


    @Override
    public Collection<Instalation> printAll() {
        log.info("Getting all instalations --repository");
        return em.createQuery(" from Instalation", Instalation.class).getResultList();
    }


    @Override
    public Instalation printbyid(int id) {
        log.info("Getting instalation by id --repository " + id);
        return em.find(Instalation.class, id);
    }

    public Collection<Instalation> getByPVModule(PVModule pv) {
        log.info("Getting instalations by pvModule --repository " + pv);
        return em.createQuery(" from Instalation I  WHERE I.pvModule=:id", Instalation.class)
                .setParameter("id", pv).getResultList();
    }

    public Collection<Instalation> getByConstruction(Construction construction) {
        log.info("Getting instalations by construction --repository " + construction);
        return em.createQuery(" from Instalation I  WHERE I.construction=:con", Instalation.class)
                .setParameter("con", construction).getResultList();
    }


    public Collection<Instalation> getByInverter(Inverter inverter) {
        log.info("Getting instalations by inverter --repository " + inverter);
        return em.createQuery(" from Instalation I WHERE I.inverter=:inv", Instalation.class)
                .setParameter("inv", inverter).getResultList();
    }
}

