package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Production;
import com.pvapp.PVApp.Repositories.CRUD;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Slf4j
@Repository
public class ProductionDBRepository implements CRUD<Production> {

    @PersistenceContext
    EntityManager em;


    @Override
    @Transactional
    public void update(Production production) {
        log.info("Merging production --repository");
        em.merge(production);
    }

    @Override
    @Transactional
    public void create(Production production) {
        log.info("Saving production --repository");
        em.persist(production);
    }

    @Override
    @Transactional
    public void delete(int id) {
        log.info("Deleting production by id --repository "+ id);
        em.remove(em.find(Production.class, id));
    }

    @Override
    public Collection<Production> printAll() {
        log.info("Getting all productions --repository");
        return em.createQuery("FROM Production", Production.class).getResultList();
    }

    @Override
    public Production printbyid(int id) {
        log.info("Getting production by id --repository " + id);
        return em.find(Production.class, id);
    }


}
