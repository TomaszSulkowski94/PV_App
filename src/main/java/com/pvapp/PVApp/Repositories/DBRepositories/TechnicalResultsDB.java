package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.TechnicalResults;
import com.pvapp.PVApp.Repositories.CRUD;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Slf4j
public class TechnicalResultsDB implements CRUD<TechnicalResults> {


    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void update(TechnicalResults technicalResults) {
        log.info("Merging technical results form --repository");
        em.merge(technicalResults);
    }

    @Override
    @Transactional
    public void create(TechnicalResults technicalResults) {
        log.info("Creating technical results --repository");
        em.persist(technicalResults);
    }

    @Override
    @Transactional
    public void delete(int id) {
        log.info("Deleting technical results by id --repository: " + id);
        em.remove(em.find(TechnicalResults.class,id));
    }

    @Override
    public Collection<TechnicalResults> printAll() {
        log.info("Getting technical results --repository");
        return em.createQuery("From TechnicalResults", TechnicalResults.class).getResultList();
    }

    @Override
    public TechnicalResults printbyid(int id) {
        log.info("Getting technical results by id --repository: " + id);
        return em.find(TechnicalResults.class,id);
    }
}
