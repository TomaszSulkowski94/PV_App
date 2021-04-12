package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Repositories.CRUD;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
