package com.pvapp.PVApp.Repositories.DBRepositories;

import com.pvapp.PVApp.Entities.Price;
import com.pvapp.PVApp.Repositories.CRUD;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Slf4j
@Repository
public class PriceDBRepo implements CRUD<Price> {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void update(Price price) {
        log.info("Merging price --repository");
        em.merge(price);

    }

    @Override
    @Transactional
    public void create(Price price) {
        log.info("Creating price --repository");
        em.persist(price);
    }

    @Override
    @Transactional
    public void delete(int id) {
        log.info("Deleting price --repository");
        em.remove(em.find(Price.class, id));
    }

    @Override
    public Collection<Price> printAll() {
        log.info("Getting price list --repository");
        return em.createQuery("FROM Price", Price.class).getResultList();
    }

    @Override
    public Price printbyid(int id) {
        log.info("Getting price by id --repository");
        return em.find(Price.class, id);
    }
}
